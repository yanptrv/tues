"""Stuff"""
from django.db import migrations, models


class Migration(migrations.Migration):
    """Migrations"""
    dependencies = [
        ('elsys', '0003_alter_car_brand'),
    ]

    operations = [
        migrations.AddField(
            model_name='car',
            name='is_electric',
            field=models.BooleanField(default=True),
        ),
        migrations.AlterField(
            model_name='car',
            name='id',
            field=models.AutoField(auto_created=True, primary_key=True, serialize=False,
                                   verbose_name='ID'),
        ),
    ]
