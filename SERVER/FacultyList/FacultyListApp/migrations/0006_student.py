# Generated by Django 5.2.3 on 2025-06-19 18:39

import django.db.models.deletion
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('FacultyListApp', '0005_alter_group_faculty_delete_student'),
    ]

    operations = [
        migrations.CreateModel(
            name='Student',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('last_name', models.CharField(max_length=100)),
                ('first_name', models.CharField(max_length=100)),
                ('middle_name', models.CharField(blank=True, max_length=100, null=True)),
                ('birth_date', models.DateField()),
                ('phone', models.CharField(max_length=20)),
                ('sex', models.IntegerField(choices=[(0, 'Не указано'), (1, 'Мужской'), (2, 'Женский')], default=0)),
                ('group', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='students', to='FacultyListApp.group')),
            ],
        ),
    ]
