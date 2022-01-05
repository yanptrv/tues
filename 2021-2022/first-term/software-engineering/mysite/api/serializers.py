"""Serializers import"""
from rest_framework import serializers
from elsys.models import Car


class CarSerializer(serializers.ModelSerializer):
    """Car serializer class"""

    class Meta:
        """Meta class"""
        model = Car
        fields = ['id', 'color', 'brand', 'description', 'made']
        read_only_fields = ['id', ]
