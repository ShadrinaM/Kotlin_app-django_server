from rest_framework import serializers
from .models import *

class FacultySerializer(serializers.ModelSerializer):
    class Meta:
        model = Faculty
        fields = ['id', 'name']

class GroupSerializer(serializers.ModelSerializer):
    class Meta:
        model = Group
        fields = ['id', 'name', 'faculty']


# class StudentSerializer(serializers.ModelSerializer):
#     class Meta:
#         model = Student
#         fields = ['id', 'last_name', 'first_name', 'middle_name', 'birth_date', 'phone', 'sex', 'group']

# class StudentSerializer(serializers.ModelSerializer):
#     class Meta:
#         model = Student
#         fields = ['id', 'last_name', 'first_name', 'middle_name', 'birth_date', 'group', 'phone', 'sex']

# class StudentSerializer(serializers.ModelSerializer):
#     class Meta:
#         model = Student
#         fields = ['id', 'last_name', 'first_name', 'middle_name', 
#                  'birth_date', 'group', 'phone', 'sex']

class StudentSerializer(serializers.ModelSerializer):
    birth_date = serializers.DateField(format="%Y-%m-%d", input_formats=["%Y-%m-%d"])
    
    class Meta:
        model = Student
        fields = ['id', 'last_name', 'first_name', 'middle_name', 
                 'birth_date', 'group', 'phone', 'sex']