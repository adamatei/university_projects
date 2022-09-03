from django.db import models
from user.models import User

class SubmittedData(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    data = models.TextField(max_length=1000)
    approved = models.BooleanField(default=False)


class DataRelationships(models.Model):
    data = models.ForeignKey('SubmittedData', on_delete=models.CASCADE)
    related_data_name = models.TextField(max_length=1000)

    