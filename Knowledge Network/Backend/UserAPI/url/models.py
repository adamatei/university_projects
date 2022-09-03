from django.db import models
from user.models import User

# from ..user.models import User


class SubmittedUrl(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    url = models.URLField()
    approved = models.BooleanField(default=False)
