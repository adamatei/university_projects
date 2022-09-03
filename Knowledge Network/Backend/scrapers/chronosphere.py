import requests
import re

URL = "https://smartvenue.nl/en/de-projecten/"
r = requests.get(URL)
CLEANR = re.compile('<.*?>') 

ws_object = {}

data = re.findall('<a class="lsw_logo_link" href="(http|https:\/\/[\w_-]+(?:(?:\.[\w_-]+)+)[\w.,@?^=%&:\/~+#-]*[\w@?^=%&\/~+#-])"', str(r.content))

def clean_text(text):
    text = text.replace('\\xe2', '')
    text = text.replace('\\x80', '')
    text = text.replace('\\x98', '')
    text = text.replace('\\x99', '')
    text = text.replace('\\xc3', '')
    text = text.replace('\\xab', '')
    text = text.replace('\\xc2', '')
    text = text.replace('\\xa0', '')
    text = text.replace('&nbsp;', '')
    text = text.replace('&#8217;', "'")
    text = re.sub(CLEANR, '', text)

    return text

token = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjU0MTcxNjQ1LCJpYXQiOjE2NTQxNzEzNDUsImp0aSI6IjA2ZTgzMWNlMjc3MzQzODE5YzEwZjQ1MDdiODZlZDkxIiwidXNlcl9pZCI6MSwicm9sZSI6IlVzZXIifQ.Bz2fbFJ56YdreUZI0ZActx4yRUdKhefN7IndFi9EISo'

def post_nodes(node_a, node_b, relationship_type):
    requests.post('http://localhost:5000/graphs/nodes', headers={"Authorization": "Bearer " + token}, json={"data": node_a.replace(" ", "_"), "relationships": []})
    requests.post('http://localhost:5000/graphs/nodes', headers={"Authorization": "Bearer " + token}, json={"data": node_b.replace(" ", "_"), "relationships": []})
    requests.post('http://localhost:5000/graphs/relationships', headers={"Authorization": "Bearer " + token}, json={"first_node": node_a.replace(" ", "_"), "second_node": node_b.replace(" ", "_"), "relationship_type": relationship_type})


requests.post('http://localhost:5000/graphs/nodes', headers={"Authorization": "Bearer " + token}, json={"data": "Chronosphere", "relationships": []})

for d in data:
    r=requests.get(d)
    content = str(r.content.decode('utf-8')).strip()
    content = content.replace(r'\n', '')
    content = content.replace(r'\t', '')
    title = re.findall('<h1 class="entry-title".*?>(.+?) &#8211', content)
    title = clean_text(title[0])

    #post_nodes(d, title, "LINKS")

    requests.post('http://localhost:5000/graphs/relationships', headers={"Authorization": "Bearer " + token}, json={"first_node": "Chronosphere", "second_node": title.replace(" ", "_"), "relationship_type": "BELONGS_TO"})


    text = re.findall('<div class="entry-content".*?>([^+]+?)<\/div>', content)
    p_tags = re.findall('<p.*?>(.+?)<\/p>', str(text[0]))
    li_tags = re.findall('<li.*?>(.+?)<\/li>', str(text[0]))
    text = ""
    for p in p_tags:
        
        tags = clean_text(p)
        tags = tags + " "
        text = text + tags

    for li in li_tags:
        
        tags = clean_text(li)
        tags = tags + " "
        text = text + tags
    
    ws_object[d]=text
