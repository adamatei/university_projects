from django.http import HttpResponse
import requests
from rest_framework.generics import GenericAPIView, RetrieveUpdateDestroyAPIView
from rest_framework.response import Response
from rest_framework import status

from .serializers import SubmittedDataSerializer
from .models import DataRelationships, SubmittedData

GRAPH_API_URL = "http://graph-api:5001"


class SubmittedDataListController(GenericAPIView):
    serializer_class = SubmittedDataSerializer

    # get all submitted data
    def get(self, request):
        list = []
        for data in SubmittedData.objects.all():
            relationships = DataRelationships.objects.filter(data_id=data.id)
            data.relationships = relationships

            list.append(data)

        serializer = SubmittedDataSerializer(list, many=True)
        return Response(serializer.data)

    # create new data
    def post(self, request):
        data = request.data

        try:
            relationships = data["relationships"]

            new_data = SubmittedData.objects.create(
                data=data["data"],
                user_id=data["user_id"],
            )

            for relationship in relationships:
                DataRelationships.objects.create(
                    related_data_name=relationship["related_data_name"],
                    data_id=new_data.id,
                )

        except KeyError:
            return Response(status=status.HTTP_500_INTERNAL_SERVER_ERROR)

        return Response(data, status=status.HTTP_201_CREATED)


class SubmittedDataDetailsController(RetrieveUpdateDestroyAPIView):
    serializer_class = SubmittedDataSerializer
    lookup_url_kwarg = "id"
    queryset = SubmittedData.objects.all()

    # get data by id
    def retrieve(self, request, *args, **kwargs):
        obj = self.get_object()

        relationships = DataRelationships.objects.filter(data_id=obj.id)

        obj.relationships = relationships

        serializer = SubmittedDataSerializer(obj)
        return Response(serializer.data)


class SubmittedDataApprovalController(GenericAPIView):
    serializer_class = SubmittedDataSerializer
    queryset = SubmittedData.objects

    # create new data
    def post(self, request, pk):
        try:
            submittedData = self.queryset.get(pk=pk)
        except SubmittedData.DoesNotExist:
            return Response(status=status.HTTP_404_NOT_FOUND)

        relationships = DataRelationships.objects.filter(data_id=submittedData.id)
        submittedData.relationships = relationships

        resp = requests.post(
            f"{GRAPH_API_URL}/graphs/nodes/",
            headers={"accept": "application/data"},
            json=SubmittedDataSerializer(submittedData).data,
        )
        if resp.status_code == status.HTTP_200_OK:
            submittedData.delete()
            return Response(status=status.HTTP_200_OK)

        return Response(status=status.HTTP_400_BAD_REQUEST)
