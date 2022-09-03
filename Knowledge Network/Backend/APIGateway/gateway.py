from crypt import methods
from flask import Flask, abort, Response, request
import requests
from flask_cors import CORS
import jwt

app = Flask(__name__)
CORS(app)


USER_API_URL = "http://user-api:8000"
GRAPH_API_URL = "http://graph-api:5001"


def authorize(token):
    if token is None:
        abort(Response("No token found", 400))
    token = token.split(" ")[1]

    authorize = requests.post(
        USER_API_URL + "/v1/token/verify/",
        headers={"accept": "application/json"},
        json={"token": token},
    )
    if authorize.status_code == 401:
        abort(401)

    decoded_payload = jwt.decode(
        token,
        "django-insecure-!%j1ia(xismz_v_mzpe6fr^44r-_t#$b7ne_7eu+57wl5uhsbh",  # taken from django config SECRET_KEY
        algorithms=["HS256"],
    )
    return decoded_payload["role"]


def is_admin(auth_header):
    if authorize(auth_header) == "Admin":
        return True
    else:
        return False


def is_admin_or_curator(auth_header):
    role = authorize(auth_header)
    if role == "Admin" or role == "Curator":
        return True
    else:
        return False

# POST for logging in
@app.route("/login/", methods=["POST"])
def login():
    if request.method == "POST":
        data = request.get_json()
        resp = requests.post(
            USER_API_URL + "/v1/token/login/",
            headers={"accept": "application/json"},
            json=data,
        )
        return (resp.text, resp.status_code, resp.headers.items())

# POST for refreshing existing JWT 
@app.route("/token/refresh/", methods=["POST"])
def refresh():
    if request.method == "POST":
        data = request.get_json()
        resp = requests.post(
            USER_API_URL + "/v1/token/refresh/",
            headers={"accept": "application/json"},
            json=data,
        )
        return (resp.text, resp.status_code, resp.headers.items())

# POST/GET users
@app.route("/users/", methods=["GET", "POST"])
def users():
    if request.method == "GET":
        if not is_admin(request.headers.get("Authorization")):
            return abort(403)
        resp = requests.get(USER_API_URL + "/v1/users/")

        return (resp.text, resp.status_code, resp.headers.items())

    if request.method == "POST":
        data = request.get_json()
        resp = requests.post(
            USER_API_URL + "/v1/users/",
            headers={"accept": "application/json"},
            json=data,
        )

        return (resp.text, resp.status_code, resp.headers.items())

# GET/PUT/DELETE for users by id
@app.route("/users/<user_id>/", methods=["GET", "PUT", "DELETE"])
def user(user_id):
    if not is_admin(request.headers.get("Authorization")):
        return abort(403)

    if request.method == "GET":
        resp = requests.get(f"{USER_API_URL}/v1/users/{user_id}/")
    if request.method == "PUT":
        data = request.get_json()
        resp = requests.put(
            f"{USER_API_URL}/v1/users/{user_id}/",
            headers={"accept": "application/json"},
            json=data,
        )
    if request.method == "DELETE":
        resp = requests.delete(f"{USER_API_URL}/v1/users/{user_id}/")
    return (resp.text, resp.status_code, resp.headers.items())

# POST/GET labels
@app.route("/labels/", methods=["GET", "POST"])
def labels():
    if request.method == "GET":
        resp = requests.get(USER_API_URL + "/v1/labels/")

        return (resp.text, resp.status_code, resp.headers.items())

    if request.method == "POST":
        if not is_admin_or_curator(request.headers.get("Authorization")):
            return abort(403)

        data = request.get_json()
        resp = requests.post(
            USER_API_URL + "/v1/labels/",
            headers={"accept": "application/json"},
            json=data,
        )

        return (resp.text, resp.status_code, resp.headers.items())

# GET/DELETE labels by id
@app.route("/labels/<label_id>/", methods=["GET", "DELETE"])
def label(label_id):
    if request.method == "GET":
        resp = requests.get(f"{USER_API_URL}/v1/labels/{label_id}/")

    if request.method == "DELETE":
        if not is_admin(request.headers.get("Authorization")):
            return abort(403)
        resp = requests.delete(f"{USER_API_URL}/v1/labels/{label_id}/")

    return (resp.text, resp.status_code, resp.headers.items())

# GET labels by name
@app.route("/labels/search/<label_name>/", methods=["GET"])
def label_search(label_name):
    if request.method == "GET":
        resp = requests.get(f"{USER_API_URL}/v1/labels/search/{label_name}/")

    return (resp.text, resp.status_code, resp.headers.items())


# POST/GET for urls submissions
@app.route("/urls/", methods=["GET", "POST"])
def urls():
    if request.method == "GET":
        if not is_admin_or_curator(request.headers.get("Authorization")):
            return abort(403)
        resp = requests.get(USER_API_URL + "/v1/urls/")

        return (resp.text, resp.status_code, resp.headers.items())

    if request.method == "POST":

        data = request.get_json()
        resp = requests.post(
            USER_API_URL + "/v1/urls/",
            headers={"accept": "application/json"},
            json=data,
        )

        return (resp.text, resp.status_code, resp.headers.items())

# PUT/DELETE url submissions by id
@app.route("/urls/<url_id>/", methods=["PUT", "DELETE"])
def url(url_id):
    if not is_admin_or_curator(request.headers.get("Authorization")):
        return abort(403)

    if request.method == "PUT":
        data = request.get_json()
        resp = requests.put(
            f"{USER_API_URL}/v1/urls/{url_id}/",
            headers={"accept": "application/json"},
            json=data,
        )
    if request.method == "DELETE":
        resp = requests.delete(f"{USER_API_URL}/v1/urls/{url_id}/")

    return (resp.text, resp.status_code, resp.headers.items())

# POST/GET/PUT/DELETE nodes in neo4j
@app.route("/graphs/nodes/", methods=["GET", "PUT", "DELETE", "POST"])
def graph_nodes():
    if request.method == "GET":
        authorize(request.headers.get("Authorization"))
        resp = requests.get(f"{GRAPH_API_URL}/graphs/nodes/")
        return (resp.text, resp.status_code, resp.headers.items())
    elif request.method == "PUT":
        authorize(request.headers.get("Authorization"))
        data = request.get_json()
        resp = requests.put(f"{GRAPH_API_URL}/graphs/nodes/", json=data)
        return (resp.text, resp.status_code, resp.headers.items())
    elif request.method == "DELETE":
        authorize(request.headers.get("Authorization"))
        data = request.get_json()
        resp = requests.delete(f"{GRAPH_API_URL}/graphs/nodes/", json=data)
        return (resp.text, resp.status_code, resp.headers.items())
    else:
        authorize(request.headers.get("Authorization"))
        data = request.get_json()
        resp = requests.post(f"{GRAPH_API_URL}/graphs/nodes/", json=data)
        return (resp.text, resp.status_code, resp.headers.items())

# GET nodes by name
@app.route("/graphs/nodes/<node_name>/", methods=["GET"])
def graphs_nodes_by_name(node_name):
    resp = requests.get(f"{GRAPH_API_URL}/graphs/nodes/{node_name}/")
    return (resp.text, resp.status_code, resp.headers.items())

# POST/GET for nodes' relationships
@app.route("/graphs/relationships/", methods=["GET", "POST"])
def graph_relationships():
    if request.method == "GET":
        authorize(request.headers.get("Authorization"))
        resp = requests.get(f"{GRAPH_API_URL}/graphs/relationships/")
        return (resp.text, resp.status_code, resp.headers.items())
    else:
        authorize(request.headers.get("Authorization"))
        data = request.get_json()
        resp = requests.post(f"{GRAPH_API_URL}/graphs/relationships/", json=data)
        return (resp.text, resp.status_code, resp.headers.items())

# POST/GET for submitted data
@app.route("/data/", methods=["GET", "POST"])
def data_list():
    if request.method == "GET":
        resp = requests.get(USER_API_URL + "/v1/data/")

        return (resp.text, resp.status_code, resp.headers.items())

    if request.method == "POST":
        data = request.get_json()
        resp = requests.post(
            USER_API_URL + "/v1/data/",
            headers={"accept": "application/json"},
            json=data,
        )

        return (resp.text, resp.status_code, resp.headers.items())

# GET/PUT/DELETE for submitted data by id
@app.route("/data/<data_id>/", methods=["GET", "PUT", "DELETE"])
def data(data_id):
    if not is_admin(request.headers.get("Authorization")):
        return abort(403)

    if request.method == "GET":
        resp = requests.get(f"{USER_API_URL}/v1/data/{data_id}/")
    if request.method == "PUT":
        data = request.get_json()
        resp = requests.put(
            f"{USER_API_URL}/v1/data/{data_id}/",
            headers={"accept": "application/json"},
            json=data,
        )
    if request.method == "DELETE":
        resp = requests.delete(f"{USER_API_URL}/v1/data/{data_id}/")
    return (resp.text, resp.status_code, resp.headers.items())

# PUT for submitted data by id
@app.route("/data/approve/<data_id>/", methods=["PUT"])
def approve_data(data_id):
    data = request.get_json()
    resp = requests.post(
        f"{USER_API_URL}/v1/data/approve/{data_id}",
        headers={"accept": "application/json"},
        json=data,
    )
    return (resp.text, resp.status_code, resp.headers.items())

# GET for nodes' relationships' types
@app.route("/graphs/relationships/types/", methods=["GET"])
def graph_relationships_types():
    authorize(request.headers.get("Authorization"))
    resp = requests.get(f"{GRAPH_API_URL}/graphs/relationships/types/")
    return (resp.text, resp.status_code, resp.headers.items())