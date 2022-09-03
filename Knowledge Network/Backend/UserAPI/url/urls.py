from django.urls import path
from . import views

urlpatterns = [
    path("urls/", views.SubmittedUrlListController.as_view()),
    path("urls/<int:pk>/", views.SubmittedUrlDetailController.as_view()),
]
