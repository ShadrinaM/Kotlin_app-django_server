# Generated by Django 5.2.3 on 2025-06-19 18:51

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('FacultyListApp', '0006_student'),
    ]

    operations = [
        migrations.AlterField(
            model_name='student',
            name='sex',
            field=models.IntegerField(choices=[(0, 'Не указан'), (1, 'Мужской'), (2, 'Женский')], default=0),
        ),
    ]
