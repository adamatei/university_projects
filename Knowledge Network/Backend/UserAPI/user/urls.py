from django.urls import path
from . import views

urlpatterns = [
    path("users/", views.UserListController.as_view()),
    path("users/<int:pk>/", views.UserDetailController.as_view()),
    path("labels/", views.LabelListController.as_view()),
    path("labels/<int:pk>/", views.LabelDetailController.as_view()),
    path("labels/search/<slug:looked_string>/", views.LabelSearchController.as_view()),
    
]
