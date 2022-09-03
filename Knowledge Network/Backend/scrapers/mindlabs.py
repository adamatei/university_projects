from platform import node
from venv import create
import requests
import re

projects = [('https://www.mind-labs.eu/projecten/natural-language-data/', 'NATURAL LANGUAGE & DATA'), ('https://www.mind-labs.eu/projecten/robotics-avatars/', 'ROBOTICS & AVATARS'), 
('https://www.mind-labs.eu/projecten/virtual-augmented-reality/', 'VIRTUAL & AUGMENTED REALITY'), ('https://www.mind-labs.eu/projecten/serious-games-learning/', 'SERIOUS GAMING & LEARNING'),
('https://www.mind-labs.eu/projecten/overige-projecten/health-innovation-brabant/', 'HEALTH INNOVATION BRABANT'), ('https://www.mind-labs.eu/projecten/overige-projecten/digireal-xl/', 'DIGIREAL-XL'),
('https://www.mind-labs.eu/projecten/overige-projecten/castlab/', 'CASTLAB'), ('https://www.mind-labs.eu/projecten/overige-projecten/drive-mkb/', 'DRIVE MKB'),
('https://www.mind-labs.eu/projecten/overige-projecten/thebe/de-wever/', 'THEBE/DE WEVER'), ('https://www.mind-labs.eu/projecten/overige-projecten/cooerdinatie-dynamiek/', 'COÖRDINATIE DYNAMIEK')]

token = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjU0MTY5MjEyLCJpYXQiOjE2NTQxNjg5MTIsImp0aSI6ImViMTg4N2Q5MzlkNDQxNGJiZTMzM2VjYWJiMGViZjgyIiwidXNlcl9pZCI6MSwicm9sZSI6IlVzZXIifQ.z8bVfnOZNQr-yK3LPjd1AAHkf5VBi6aMCgYq1LGkmDA'

def clean_text(text):
    text = text.replace('\\xe2', '')
    text = text.replace('\\x80', '')
    text = text.replace('\\x98', '')
    text = re.sub('<.+?>', '', text)
    text = text.replace('&nbsp;', '')
    text = text.replace('&nbsp', '')
    text = text.replace('👉', '')
    text = text.replace('\u202f', '')
    return text.strip()

def set_object(d, p):
    if d == p:
        f_object[p] = ''
    else:
        f_object[p][d] = ''

def get_object(d, p):
    if d == p:
        return f_object[p]
    return f_object[p][d]

def add_to_object(d, p, value):
    if d == p:
        f_object[p] += value
    else:
        f_object[p][d] += value

f_object = {}

def post_nodes(node_a, node_b, relationship_type):
    requests.post('http://localhost:5000/graphs/nodes', headers={"Authorization": "Bearer " + token}, json={"data": node_a.replace(" ", "_"), "relationships": []})
    requests.post('http://localhost:5000/graphs/nodes', headers={"Authorization": "Bearer " + token}, json={"data": node_b.replace(" ", "_"), "relationships": []})
    requests.post('http://localhost:5000/graphs/relationships', headers={"Authorization": "Bearer " + token}, json={"first_node": node_a.replace(" ", "_"), "second_node": node_b.replace(" ", "_"), "relationship_type": relationship_type})
    
requests.post('http://localhost:5000/graphs/nodes', headers={"Authorization": "Bearer " + token}, json={"data": "Mindlabs", "relationships": []})

for p in projects:
    r = requests.get(p[0])
    f_object[p[1]] = {}
    post_nodes(p[0], p[1], "LINKS")
    requests.post('http://localhost:5000/graphs/relationships', headers={"Authorization": "Bearer " + token}, json={"first_node": "Mindlabs", "second_node": p[1].replace(" ", "_"), "relationship_type": "BELONGS_TO"})
    data = re.findall('<nav class="nav-sub">(.+)<\/nav>', str(r.content.decode('utf-8')))
    if data:
        data = re.findall('href="(\/projecten\/(overige-projecten\/)?[\/a-z-]+)\/" title="(.+?)"', data[0])
        data = [('https://www.mind-labs.eu' + x[0], x[2]) for x in data]
    else:
        data = [(p[0], p[1])]
    for d in data:
        set_object(d[1], p[1])
        r = requests.get(d[0])
        if d[1] != p[1]:
            post_nodes(d[0], d[1], "LINKS")
            requests.post('http://localhost:5000/graphs/relationships', headers={"Authorization": "Bearer " + token}, json={"first_node": d[1].replace(" ", "_"), "second_node": p[1].replace(" ", "_"), "relationship_type": "BELONGS_TO"})
        content = str(r.content.decode('utf-8')).strip()
        text = re.findall('<div class="inner">([^+]+?)</div></div>', content)
        paragraphs = re.findall('<p.*?>(.+?)<\/p>', text[0].strip())
        for t in paragraphs:
            add_to_object(d[1], p[1], clean_text(t))
        lists = re.findall('<li.*?>(.+?)<\/li>', text[0].strip())
        for t in lists:
            add_to_object(d[1], p[1], clean_text(t))
    f = open("mindlabs.txt", "w")
    f.write(str(f_object))
    f.close()

print(f_object)