from rest_framework import serializers
from .models import SubmittedData, DataRelationships

class DataRelationshipSerializer(serializers.ModelSerializer):
    class Meta:
        model = DataRelationships
        fields = "__all__"

class SubmittedDataSerializer(serializers.ModelSerializer):
    relationships = DataRelationshipSerializer(many=True, read_only=True)

    class Meta:
        model = SubmittedData
        fields = ['id', 'data', 'approved', 'relationships']
