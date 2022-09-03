from .models import User
from rest_framework import status
from rest_framework.test import APITestCase
import json

def create_mock_product_object():
    return {
        "first_name": "string",
        "last_name": "string",
        "email": "user@xxxxxample.com",
        "password": "string",
        "job_title": "string",
        "role": "string",
        "topology_labels": [
  ]
    }

def create_faulty_mock_object():
    return {
        "first_name": "string",
        "email": "user@example.com",
        "password": "string",
        "topology_labels": [
  ]
    }


class UserAPITestCase(APITestCase):
    url = "/v1/users/"
    def test_post_201(self):
        data = create_mock_product_object()
        response = self.client.post(
            self.url, data=json.dumps(data), content_type="application/json"
        )

        self.assertEqual(response.status_code, status.HTTP_201_CREATED)

    def test_post_500(self):
        data = create_faulty_mock_object()

        response = self.client.post(
            self.url, data=json.dumps(data), content_type="application/json"
        )

        self.assertEqual(response.status_code, status.HTTP_400_BAD_REQUEST)

    def test_get_empty_200(self):
        response = self.client.get(self.url)

        self.assertEqual(response.status_code, status.HTTP_200_OK)

    def test_get_200(self):
        data = create_mock_product_object()

        response = self.client.post(
            self.url, data=json.dumps(data), content_type="application/json"
        )

        get_response = self.client.get(self.url)

        self.assertEqual(get_response.status_code, status.HTTP_200_OK)

class UserDetailsAPITestCase(APITestCase):
    url = "/v1/users/"
    def test_get_id_200(self):
        data = create_mock_product_object()
        response = self.client.post(
            self.url, data=json.dumps(data), content_type="application/json"
        )
        id = "1"

        get_response = self.client.get(self.url + id + "/")
        self.assertEqual(get_response.status_code, status.HTTP_200_OK)

    def test_get_id_404(self):
        id = "-1"
        get_response = self.client.get(self.url + id + "/")

        self.assertEqual(get_response.status_code, status.HTTP_404_NOT_FOUND)

    def test_put_200(self):
        data = create_mock_product_object()
        response = self.client.post(
            self.url, data=json.dumps(data), content_type="application/json"
        )
        data['first_name']="CHANGED"
        id = "1"

        put_response = self.client.put(
            self.url+id+"/", data=json.dumps(data), content_type="application/json"
        )
        self.assertEqual(put_response.status_code, status.HTTP_200_OK)
    
    def test_put_404(self):
        data = create_mock_product_object()

        id = "-1"

        put_response = self.client.put(
            self.url+id+"/", data=json.dumps(data), content_type="application/json"
        )
        self.assertEqual(put_response.status_code, status.HTTP_404_NOT_FOUND)

    def test_delete_204(self):
        data = create_mock_product_object()
        response = self.client.post(
            self.url, data=json.dumps(data), content_type="application/json"
        )
        id = "1"

        put_response = self.client.delete(
            self.url+id+"/")
        self.assertEqual(put_response.status_code, status.HTTP_204_NO_CONTENT)

    def test_delete_404(self):
        id = "-1"

        put_response = self.client.delete(
            self.url+id+"/")
        self.assertEqual(put_response.status_code, status.HTTP_404_NOT_FOUND)