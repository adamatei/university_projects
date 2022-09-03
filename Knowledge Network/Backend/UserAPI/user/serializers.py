from rest_framework import serializers
from .models import User,Label

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = "__all__"
        
class LabelSerializer(serializers.ModelSerializer):
    class Meta:
        model = Label
        fields = "__all__"