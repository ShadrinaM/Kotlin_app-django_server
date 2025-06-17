from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response
from .models import Faculty
from .serializers import FacultySerializer


@api_view(['GET'])
def get_faculties(request):
    faculties = Faculty.objects.all()
    serializer = FacultySerializer(faculties, many=True)
    return Response(serializer.data)


@api_view(['POST'])
def post_faculty(request):
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
        return Response(status=status.HTTP_404_NOT_FOUND)