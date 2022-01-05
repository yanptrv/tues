"""Stuff"""
from django.db import migrations, models


class Migration(migrations.Migration):
    """Migrations"""
    dependencies = [
        ('elsys', '0002_alter_car_brand'),
    ]

    operations = [
        migrations.AlterField(
            model_name='car',
            name='brand',
            field=models.CharField(max_length=50),
        ),
    ]
