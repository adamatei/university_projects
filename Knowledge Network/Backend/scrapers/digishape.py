import requests


scraper = {
  "Data assimilation and water quality": "https://www.digishape.nl/projecten/data-assimilation-and-water-quality-simulation-for-geosr-korea",
  "Risicogestuurd Scheepvaart Verkeersmanagement op de Noordzee": "https://www.digishape.nl/projecten/risicogestuurd-scheepvaart-verkeersmanagement-op-de-noordzee",
  "Het Dotterproject": "https://www.digishape.nl/projecten/het-dotterproject",
  "Development of Delft3D-GT": "https://www.digishape.nl/projecten/development-of-delft3d-gt",
  "Rekenen aan Slim Watermanagement in de praktijk": "https://www.digishape.nl/projecten/rekenen-aan-slim-watermanagement-in-de-praktijk",
  "Betrouwbare overstromings­kaarten op basis van Social Media": "https://www.digishape.nl/projecten/betrouwbare-overstromingskaarten-op-basis-van-social-media",
  "Community Model XBeach voor Kusttoepassingen": "https://www.digishape.nl/projecten/community-model-xbeach-voor-kusttoepassingen",
  "Groundwater Chautauqua Project": "https://www.digishape.nl/projecten/groundwater-chautauqua-project-groundwater-security-information-system",
  "Vervolgonderzoek meerwaarde CoVadem in de haven": "https://www.digishape.nl/projecten/vervolgonderzoek-meerwaarde-covadem-in-de-haven",
  "AI analyse video Klaverbank": "https://www.digishape.nl/projecten/ai-analyse-video-klaverbank",
  "Markermeer": "https://www.digishape.nl/projecten/markermeer",
  "Digitwin Noordzee": "https://www.digishape.nl/projecten/digitwin-noordzee",
  "Rivierdiepte": "https://www.digishape.nl/projecten/rivierdiepte"
}

token = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjU0MTcyNzUzLCJpYXQiOjE2NTQxNzI0NTMsImp0aSI6IjIwMTVjMzFhYmUzNjRiOTFhZDJjOWY1NDk0ZmM2OWQ2IiwidXNlcl9pZCI6MSwicm9sZSI6IlVzZXIifQ.KObAUi4xqVwIFdcSQGve0MZ-OX86QP_wENFB5F_qx1w'

def post_nodes(node_a, node_b, relationship_type):
    requests.post('http://localhost:5000/graphs/nodes', headers={"Authorization": "Bearer " + token}, json={"data": node_a.replace(" ", "_"), "relationships": []})
    requests.post('http://localhost:5000/graphs/nodes', headers={"Authorization": "Bearer " + token}, json={"data": node_b.replace(" ", "_"), "relationships": []})
    requests.post('http://localhost:5000/graphs/relationships', headers={"Authorization": "Bearer " + token}, json={"first_node": node_a.replace(" ", "_"), "second_node": node_b.replace(" ", "_"), "relationship_type": relationship_type})


requests.post('http://localhost:5000/graphs/nodes', headers={"Authorization": "Bearer " + token}, json={"data": "Digishape", "relationships": []})

for attr, value in scraper.items():
    print(attr, value)
    post_nodes(attr, value, "LINKS")
    requests.post('http://localhost:5000/graphs/relationships', headers={"Authorization": "Bearer " + token}, json={"first_node": attr.replace(" ", "_"), "second_node": "Digishape", "relationship_type": "BELONGS_TO"})
