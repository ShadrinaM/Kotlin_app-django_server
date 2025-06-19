from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status
from .models import *
from .serializers import *

# ФАКУЛЬТЕТЫ
@api_view(['GET'])
def get_faculties(request):
    faculties = Faculty.objects.all().order_by('name')
    serializer = FacultySerializer(faculties, many=True)
    return Response(serializer.data)

@api_view(['POST'])
def post_faculty(request):
    faculty_id = request.data.get('id', None)

    if faculty_id is not None and int(faculty_id) >= 0:
        # Обновление существующего факультета
        try:
            faculty = Faculty.objects.get(id=faculty_id)
        except Faculty.DoesNotExist:
            return Response({'error': 'Faculty not found'}, status=status.HTTP_404_NOT_FOUND)

        serializer = FacultySerializer(faculty, data=request.data)
    else:
        # Создание нового
        serializer = FacultySerializer(data=request.data)

    if serializer.is_valid():
        serializer.save()
        return Response(status=status.HTTP_200_OK)

    return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


@api_view(['DELETE'])
def delete_faculty(request, id):
    try:
        faculty = Faculty.objects.get(id=id)
        faculty.delete()
        return Response(status=status.HTTP_200_OK)
    except Faculty.DoesNotExist:
        return Response({'error': 'Not found'}, status=status.HTTP_404_NOT_FOUND)
    
# ГРУППЫ
@api_view(['GET'])
def get_groups(request):
    groups = Group.objects.all().order_by('name')
    serializer = GroupSerializer(groups, many=True)
    return Response(serializer.data)

@api_view(['POST'])
def post_group(request):
    group_id = request.data.get('id', None)
    if group_id is not None and int(group_id) >= 0:
        try:
            group = Group.objects.get(id=group_id)
        except Group.DoesNotExist:
            return Response({'error': 'Group not found'}, status=status.HTTP_404_NOT_FOUND)

        serializer = GroupSerializer(group, data=request.data)
    else:
        serializer = GroupSerializer(data=request.data)

    if serializer.is_valid():
        serializer.save()
        return Response(status=status.HTTP_200_OK)

    return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

@api_view(['DELETE'])
def delete_group(request, id):
    try:
        group = Group.objects.get(id=id)
        group.delete()
        return Response(status=status.HTTP_200_OK)
    except Group.DoesNotExist:
        return Response({'error': 'Not found'}, status=status.HTTP_404_NOT_FOUND)

# # # СТУДЕНТЫ
# # @api_view(['GET'])
# # def get_students(request):
# #     students = Student.objects.all().order_by('last_name')
# #     serializer = StudentSerializer(students, many=True)
# #     return Response(serializer.data)

# # @api_view(['POST'])
# # def post_student(request):
# #     student_id = request.data.get('id', None)
# #     if student_id is not None and int(student_id) >= 0:
# #         try:
# #             student = Student.objects.get(id=student_id)
# #         except Student.DoesNotExist:
# #             return Response({'error': 'Student not found'}, status=status.HTTP_404_NOT_FOUND)

# #         serializer = StudentSerializer(student, data=request.data)
# #     else:
# #         serializer = StudentSerializer(data=request.data)

# #     if serializer.is_valid():
# #         serializer.save()
# #         return Response(status=status.HTTP_200_OK)

# #     return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

# # @api_view(['DELETE'])
# # def delete_student(request, id):
# #     try:
# #         student = Student.objects.get(id=id)
# #         student.delete()
# #         return Response(status=status.HTTP_200_OK)
# #     except Student.DoesNotExist:
# #         return Response({'error': 'Not found'}, status=status.HTTP_404_NOT_FOUND)


# @api_view(['GET'])
# def get_students(request):
#     students = Student.objects.all().order_by('last_name')
#     serializer = StudentSerializer(students, many=True)
#     return Response(serializer.data)

# @api_view(['POST'])
# def post_student(request):
#     student_id = request.data.get('id', None)

#     if student_id is not None and int(student_id) >= 0:
#         try:
#             student = Student.objects.get(id=student_id)
#         except Student.DoesNotExist:
#             return Response({'error': 'Student not found'}, status=status.HTTP_404_NOT_FOUND)

#         serializer = StudentSerializer(student, data=request.data)
#     else:
#         serializer = StudentSerializer(data=request.data)

#     if serializer.is_valid():
#         serializer.save()
#         return Response(status=status.HTTP_200_OK)

#     return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

# @api_view(['DELETE'])
# def delete_student(request, id):
#     try:
#         student = Student.objects.get(id=id)
#         student.delete()
#         return Response(status=status.HTTP_200_OK)
#     except Student.DoesNotExist:
#         return Response({'error': 'Not found'}, status=status.HTTP_404_NOT_FOUND)


@api_view(['GET'])
def get_students(request):
    students = Student.objects.all().order_by('last_name', 'first_name')
    serializer = StudentSerializer(students, many=True)
    return Response(serializer.data)

@api_view(['POST'])
def post_student(request):
    student_id = request.data.get('id', None)
    
    if student_id is not None and int(student_id) >= 0:
        try:
            student = Student.objects.get(id=student_id)
        except Student.DoesNotExist:
            return Response({'error': 'Student not found'}, status=status.HTTP_404_NOT_FOUND)
        
        serializer = StudentSerializer(student, data=request.data)
    else:
        serializer = StudentSerializer(data=request.data)
    
    if serializer.is_valid():
        serializer.save()
        return Response(status=status.HTTP_200_OK)
    
    return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

@api_view(['DELETE'])
def delete_student(request, id):
    try:
        student = Student.objects.get(id=id)
        student.delete()
        return Response(status=status.HTTP_200_OK)
    except Student.DoesNotExist:
        return Response({'error': 'Not found'}, status=status.HTTP_404_NOT_FOUND)