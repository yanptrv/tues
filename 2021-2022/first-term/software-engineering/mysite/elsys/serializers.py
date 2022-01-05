"""Imports"""
from rest_framework import serializers
from .models import Car


class CarSerializer(serializers.ModelSerializer):
    """Car serializer class"""
    class Meta:
        """Meta"""
        model = Car
        fields = ['id', 'color', 'brand', 'description', 'made']
