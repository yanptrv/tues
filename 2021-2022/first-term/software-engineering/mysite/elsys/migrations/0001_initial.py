"""import"""
from django.db import migrations, models


class Migration(migrations.Migration):
    """Migration class"""
    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Car',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False,
                                           verbose_name='ID')),
                ('color', models.CharField(max_length=100)),
                ('made', models.DateTimeField(auto_now_add=True)),
                ('brand', models.CharField(max_length=100)),
                ('description', models.TextField()),
            ],
        ),
    ]
