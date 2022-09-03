from rest_framework.generics import GenericAPIView
from rest_framework.response import Response
from rest_framework import status
from .serializers import LabelSerializer, UserSerializer
from .models import User, Label
from django.contrib.auth.hashers import make_password
from . import validator

# Create your views here.


class UserListController(GenericAPIView):
    serializer_class = UserSerializer

    # retrieve all users
    def get(self, request):
        users = User.objects.all()
        serializer = UserSerializer(users, many=True)
        return Response(serializer.data)

    # create new user
    def post(self, request):
        new_user = request.data

        if validator.validate_email_password(request.data) == False:
            return Response("Invalid password or email")

        new_user["password"] = make_password(self.request.data["password"])
        serializer = self.serializer_class(data=new_user)
        serializer.is_valid(raise_exception=True)
        serializer.save()

        return Response(serializer.data, status=status.HTTP_201_CREATED)


class UserDetailController(GenericAPIView):
    serializer_class = UserSerializer
    queryset = User.objects

    # get user by id
    def get(self, request, pk):
        try:
            user = self.queryset.get(pk=pk)
        except User.DoesNotExist:
            return Response(status=status.HTTP_404_NOT_FOUND)

        serializer = UserSerializer(user)

        return Response(serializer.data)

    # update user
    def put(self, request, pk):

        if validator.validate_email_password(request.data) == False:
            return Response("Invalid data", status.HTTP_400_BAD_REQUEST)

        try:
            user = self.queryset.get(pk=pk)
        except User.DoesNotExist:
            return Response(status=status.HTTP_404_NOT_FOUND)

        serializer = UserSerializer(user, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)

        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    # delete user by id
    def delete(self, request, pk):
        try:
            user = self.queryset.get(pk=pk)
        except User.DoesNotExist:
            return Response(status=status.HTTP_404_NOT_FOUND)

        user.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


class LabelListController(GenericAPIView):
    serializer_class = LabelSerializer

    # get all labels
    def get(self, request):
        labels = Label.objects.all()
        serializer = LabelSerializer(labels, many=True)
        return Response(serializer.data)

    # create new label
    def post(self, request):
        new_label = request.data

        if Label.objects.filter(name=new_label["name"].strip()).exists():
            return Response(
                "Label name already exists", status=status.HTTP_409_CONFLICT
            )

        serializer = self.serializer_class(data=new_label)
        serializer.is_valid(raise_exception=True)
        serializer.save()

        return Response(serializer.data, status=status.HTTP_201_CREATED)


class LabelDetailController(GenericAPIView):
    serializer_class = LabelSerializer
    queryset = Label.objects

    # get label by id
    def get(self, request, pk):
        try:
            label = self.queryset.get(pk=pk)
        except Label.DoesNotExist:
            return Response(status=status.HTTP_404_NOT_FOUND)

        serializer = LabelSerializer(label)
        return Response(serializer.data)

    # delete label by id
    def delete(self, request, pk):
        try:
            label = self.queryset.get(pk=pk)
        except Label.DoesNotExist:
            return Response(status=status.HTTP_404_NOT_FOUND)

        label.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


class LabelSearchController(GenericAPIView):
    serializer_class = LabelSerializer

    # get label by name
    def get(self, request, looked_string):
        looked_up_labels = []
        for label in Label.objects.all():
            if looked_string.lower() in label.name.lower():
                looked_up_labels.append(label)
        serializer = LabelSerializer(looked_up_labels, many=True)
        return Response(serializer.data)
