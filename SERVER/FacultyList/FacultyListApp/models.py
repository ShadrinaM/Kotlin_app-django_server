from django.db import models

class Faculty(models.Model):
    name = models.CharField(max_length=255)

    def __str__(self):
        return self.name
    
class Group(models.Model):
    name = models.CharField(max_length=255)
    faculty = models.ForeignKey(Faculty, on_delete=models.CASCADE, related_name='groups')

    def __str__(self):
        return f"{self.name} ({self.faculty.name})"

# class Student(models.Model):
#     last_name = models.CharField(max_length=255)
#     first_name = models.CharField(max_length=255)
#     middle_name = models.CharField(max_length=255, blank=True)
#     birth_date = models.DateField()
#     phone = models.CharField(max_length=20, blank=True)
#     sex = models.IntegerField(default=0)
#     group = models.ForeignKey(Group, on_delete=models.CASCADE)

#     def __str__(self):
#         return f"{self.last_name} {self.first_name[0]}. {self.middle_name[0]}."
