"""Stuff"""
from django.db import migrations, models


class Migration(migrations.Migration):
    """Migrations"""
    dependencies = [
        ('elsys', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='car',
            name='brand',
            field=models.CharField(max_length=101),
        ),
    ]
