from django.urls import path
from . import views

urlpatterns = [
    path('', views.SubmittedDataListController.as_view()),
    path('<int:id>/', views.SubmittedDataDetailsController.as_view()),
    path('approve/<int:pk>', views.SubmittedDataApprovalController.as_view())
]