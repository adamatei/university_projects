from rest_framework import serializers
from .models import SubmittedUrl


class SubmittedUrlSerializer(serializers.ModelSerializer):
    class Meta:
        model = SubmittedUrl
        fields = ["user", "url"]


class SubmittedUrlFullSerializer(serializers.ModelSerializer):
    class Meta:
        model = SubmittedUrl
        fields = "__all__"
