import re
from django.shortcuts import render
from rest_framework.response import Response
from rest_framework.generics import GenericAPIView
from rest_framework import status
from .models import SubmittedUrl
from .serializers import SubmittedUrlSerializer, SubmittedUrlFullSerializer

# Create your views here.


class SubmittedUrlListController(GenericAPIView):

    # get all submitted urls
    def get(self, request):
        urls = SubmittedUrl.objects.all()
        serializer = SubmittedUrlFullSerializer(urls, many=True)
        return Response(serializer.data)

    # submit new url
    def post(self, request):
        submitted_url = request.data
        if SubmittedUrl.objects.filter(url=submitted_url["url"].strip()).exists():
            return Response(
                "Url has already been submitted", status=status.HTTP_409_CONFLICT
            )

        serializer = SubmittedUrlSerializer(data=submitted_url)
        serializer.is_valid(raise_exception=True)
        serializer.save()

        return Response(serializer.data, status=status.HTTP_201_CREATED)


class SubmittedUrlDetailController(GenericAPIView):

    # delete submitted url by id
    def delete(self, request, pk):
        try:
            submitted_url = SubmittedUrl.objects.get(pk=pk)
        except SubmittedUrl.DoesNotExist:
            return Response(status=status.HTTP_404_NOT_FOUND)
        submitted_url.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)

    # update submitted url by id
    def put(self, request, pk):
        try:
            submitted_url = SubmittedUrl.objects.get(pk=pk)
        except SubmittedUrl.DoesNotExist:
            return Response(status=status.HTTP_404_NOT_FOUND)

        serializer = SubmittedUrlFullSerializer(submitted_url, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)

        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
