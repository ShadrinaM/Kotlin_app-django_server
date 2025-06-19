from django.urls import path
from . import views

urlpatterns = [
    path('faculties', views.get_faculties),
    path('faculty', views.post_faculty),
    path('faculty/<int:id>', views.delete_faculty),

    path('groups', views.get_groups),
    path('group', views.post_group),
    path('group/<int:id>', views.delete_group),

    # path('students', views.get_students),
    # path('student', views.post_student),
    # path('student/<int:id>', views.delete_student),

    path('students', views.get_students),
    path('student', views.post_student),
    path('student/<int:id>', views.delete_student),
]