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

# class GroupSerializer(serializers.ModelSerializer):
#     facultyID = serializers.IntegerField(write_only=True)  # входящее поле
#     faculty = serializers.PrimaryKeyRelatedField(read_only=True)  # для возврата

#     class Meta:
#         model = Group
#         fields = ['id', 'name', 'facultyID', 'faculty']

#     def create(self, validated_data):
#         faculty_id = validated_data.pop('facultyID')
#         validated_data['faculty'] = Faculty.objects.get(id=faculty_id)
#         return super().create(validated_data)

#     def update(self, instance, validated_data):
#         faculty_id = validated_data.pop('facultyID', None)
#         if faculty_id is not None:
#             validated_data['faculty'] = Faculty.objects.get(id=faculty_id)
#         return super().update(instance, validated_data)

# class StudentSerializer(serializers.ModelSerializer):
#     class Meta:
#         model = Student
#         fields = ['id', 'last_name', 'first_name', 'middle_name', 'birth_date', 'phone', 'sex', 'group']
