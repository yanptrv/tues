"""Importing"""
from django.db import models


class Car(models.Model):
    """Car"""
    color = models.CharField(max_length=100)
    made = models.DateTimeField(auto_now_add=True)
    brand = models.CharField(max_length=50)
    description = models.TextField()
    is_electric = models.BooleanField(default=True)

    def sound_check(self):
        """Check the sound"""
        if self.is_electric:
            return ''
        return "brum brum"
