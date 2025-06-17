from django.urls import path
from . import views

urlpatterns = [
    path('faculties', views.get_faculties),
    path('faculty', views.post_faculty),
    path('faculty/int:id', views.delete_faculty),
]