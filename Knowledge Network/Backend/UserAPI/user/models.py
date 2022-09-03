from django.db import models
from django.contrib.auth.hashers import check_password, make_password
from django.contrib.auth.models import UserManager


class Label(models.Model):
    name = models.CharField(max_length=100)


def default_dict():
    return {"keywords": []}


class User(models.Model):
    first_name = models.CharField(max_length=100)
    last_name = models.CharField(max_length=100)
    email = models.EmailField(unique=True, max_length=100)
    password = models.CharField(max_length=255)
    job_title = models.CharField(max_length=100, default="")
    role = models.CharField(max_length=50, default="User")
    topology_labels = models.ManyToManyField(Label, blank=True)

    USERNAME_FIELD = "email"
    REQUIRED_FIELDS = []

    @property
    def is_anonymous(self):
        return False

    @property
    def is_authenticated(self):
        return False

    @property
    def is_active(self):
        return True

    def get_topology_labels(self):
        return ", ".join([label.name for label in self.topology_labels.all()])

    def set_password(self, raw_password):
        self.password = make_password(raw_password)

    def check_password(self, raw_password):
        return check_password(raw_password, self.password)

    objects = UserManager()

    def __str__(self):
        return "%s %s" % (self.first_name, self.last_name)
