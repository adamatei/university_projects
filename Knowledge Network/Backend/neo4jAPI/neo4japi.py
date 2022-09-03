from flask import Flask, g, request
from flask_cors import CORS
from flask_json import FlaskJSON

from neo4j import GraphDatabase, basic_auth

app = Flask(__name__)
CORS(app)
FlaskJSON(app)

driver = GraphDatabase.driver("neo4j://graph-db", auth=basic_auth("neo4j", "test"))


def get_db():
    if not hasattr(g, "neo4j_db"):
        g.neo4j_db = driver.session()
    return g.neo4j_db


def get_relationships(tx):
    return list(tx.run(f"MATCH (n)-[r]->(b) RETURN r, b"))


def get_nodes(tx):
    return list(tx.run("MATCH (n) RETURN n"))


def get_nodes_by_name(tx, name):
    # the where clause is equivalent to an insensitive string contains using regex
    return list(tx.run(f"MATCH (n) WHERE n.name =~ '(?i).*{name}.*' RETURN n"))


def set_node(tx, id, name, labels):
    return list(
        tx.run(
            f"MATCH (n) WHERE id(n) = {id} SET n.name = '{name}' {labels} RETURN n.name, labels(n) AS labels"
        )
    )


def delete_node(tx, id):
    return list(tx.run(f"MATCH (n) WHERE id(n) = {id} DETACH DELETE n"))


def create_node(tx, data):
    return list(tx.run(f"CREATE (n {{name:'{data}'}}) RETURN n"))


def create_relationship(tx, first_node, second_node, relationship_type):
    return list(
        tx.run(
            f"MATCH (a), (b) WHERE a.name = '{first_node}' AND b.name = '{second_node}' CREATE (a) - [r:{relationship_type}] -> (b) RETURN type(r)"
        )
    )


def create_relationship_scraper(tx, first_node, second_node, relationship_type):
    return list(
        tx.run(
            f"MATCH (a), (b) WHERE a.name = '{first_node}' AND b.name = '{second_node}' CREATE (a) - [r:{relationship_type}] -> (b) RETURN type(r)"
        )
    )


def relationship_types(tx):
    return list(tx.run("CALL db.relationshipTypes()"))

# POST/GET/PUT/DELETE for nodes in neo4j
@app.route("/graphs/nodes/", methods=["GET", "PUT", "DELETE", "POST"])
def graphs_n():
    if request.method == "GET":
        db = get_db()
        result = db.read_transaction(get_nodes)
        new_res = []
        for record in result:
            new_res.append(
                {
                    "node_id": record["n"].id,
                    "labels": record["n"].labels,
                    "name": record["n"]["name"],
                }
            )
        return {"results": new_res}
    elif request.method == "PUT":
        db = get_db()
        data = request.get_json()
        result = db.write_transaction(
            set_node, data["id"], data["name"], data["labels"]
        )
        new_res = []
        for record in result:
            new_res.append({"labels": record["labels"], "name": record["n.name"]})
        return {"results": new_res}
    elif request.method == "POST":
        db = get_db()
        data = request.get_json()
        db.write_transaction(create_node, data["data"])
        for node in data["relationships"]:
            db.write_transaction(
                create_relationship,
                data["data"],
                node["related_data_name"],
                "linked",
            )
        return {"results": data}
    else:
        db = get_db()
        data = request.get_json()
        result = db.write_transaction(delete_node, data["id"])
        return {}


# GET nodes by name
@app.route("/graphs/nodes/<node_name>/", methods=["GET"])
def graphs_nodes_by_name(node_name):
    db = get_db()
    result = db.read_transaction(get_nodes_by_name, node_name)
    new_res = []
    for record in result:
        new_res.append(
            {
                "node_id": record["n"].id,
                "labels": record["n"].labels,
                "name": record["n"]["name"],
            }
        )
    return {"results": new_res}

# POST/GET for nodes' relationships
@app.route("/graphs/relationships/", methods=["GET", "POST"])
def graphs_r():
    if request.method == "GET":
        db = get_db()
        result = db.read_transaction(get_relationships)
        new_res = []
        for record in result:
            first_node = record["r"].nodes[0]
            second_node = record["r"].nodes[1]
            type = record["r"].type
            new_res.append(
                {
                    "first_node": str(first_node.id),
                    "second_node": str(second_node.id),
                    "type": type,
                }
            )
        return {"results": new_res}
    db = get_db()
    data = request.get_json()
    result = db.write_transaction(
        create_relationship_scraper,
        data["first_node"],
        data["second_node"],
        data["relationship_type"],
    )
    return {"results": result}


# GET for nodes' relationships' types
@app.route("/graphs/relationships/types/", methods=["GET"])
def graphs_r_types():
    db = get_db()
    result = {"relationship_types": []}
    rel_types = db.read_transaction(relationship_types)
    for r in rel_types:
        result["relationship_types"].append(r[0])
    return result
