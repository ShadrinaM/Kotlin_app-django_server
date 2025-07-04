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


# class Student(models.Model):
#     GENDER_CHOICES = [
#         (0, 'Не указано'),
#         (1, 'Мужской'),
#         (2, 'Женский'),
#     ]
    
#     last_name = models.CharField(max_length=100)
#     first_name = models.CharField(max_length=100)
#     middle_name = models.CharField(max_length=100, blank=True, null=True)
#     birth_date = models.DateField()
#     group = models.ForeignKey(Group, on_delete=models.CASCADE, related_name='students')
#     phone = models.CharField(max_length=20)
#     sex = models.IntegerField(choices=GENDER_CHOICES, default=0)

#     def __str__(self):
#         return f"{self.last_name} {self.first_name} {self.middle_name or ''}"

class Student(models.Model):
    SEX_CHOICES = [
        (0, 'Не указан'),
        (1, 'Мужской'),
        (2, 'Женский'),
    ]
    
    last_name = models.CharField(max_length=100)
    first_name = models.CharField(max_length=100)
    middle_name = models.CharField(max_length=100, blank=True, null=True)
    birth_date = models.DateField()
    group = models.ForeignKey(Group, on_delete=models.CASCADE, related_name='students')
    phone = models.CharField(max_length=20)
    sex = models.IntegerField(choices=SEX_CHOICES, default=0)

    def __str__(self):
        return f"{self.last_name} {self.first_name}"

    @property
    def short_name(self):
        middle = f" {self.middle_name[0]}." if self.middle_name else ""
        return f"{self.last_name} {self.first_name[0]}.{middle}"