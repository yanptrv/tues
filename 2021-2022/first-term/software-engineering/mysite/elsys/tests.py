"""Importing necessary stuff"""
import json
from unittest.mock import patch
from django.test import TestCase
from elsys.processors.api_processor import ApiProcessor

COMMENTS_DATA = json.load(open('./elsys/comments.json'))
POSTS_DATA = json.load(open('./elsys/posts.json'))


class TestC(TestCase):
    """Testing Api"""

    def test_call_longest_comment(self):
        """Testing the lognest comment"""
        with patch('requests.get', return_value=COMMENTS_DATA):
            response = ApiProcessor().longest_comment()
        assert response['id'] == 3

    def test_call_longest_title(self):
        """Testing the longest tittle"""
        with patch('requests.get', return_value=POSTS_DATA):
            response = ApiProcessor().post_with_longest_title()
        assert response['id'] == 50
