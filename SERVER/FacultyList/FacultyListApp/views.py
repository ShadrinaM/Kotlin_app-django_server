from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status
from .models import Faculty
from .serializers import FacultySerializer

@api_view(['GET'])
def get_faculties(request):
    faculties = Faculty.objects.all().order_by('name')
    serializer = FacultySerializer(faculties, many=True)
    return Response(serializer.data)

# @api_view(['POST'])
# def post_faculty(request):
#     serializer = FacultySerializer(data=request.data)
#     if serializer.is_valid():
#         serializer.save()
#         return Response(status=status.HTTP_200_OK)
#     return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

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