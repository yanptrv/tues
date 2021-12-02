from django.db import models

# Create your models here.

class Car(models.Model):
    color = models.CharField(max_length=100)
    made = models.DateTimeField(auto_now_add=True)
    brand = models.CharField(max_length=50)
    description = models.TextField()
    is_electric = models.BooleanField(default = True)

    def sound_check(self):
        if self.is_electric:
            return ''
        return "brum brum"