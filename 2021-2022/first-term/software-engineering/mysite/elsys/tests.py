from django.test import TestCase
from unittest.mock import patch
from elsys.processors.api_processor import ApiProcessor
import json

COMMENTS_DATA = json.load(open('./elsys/comments.json'))
POSTS_DATA = json.load(open('./elsys/posts.json'))


class TestC(TestCase):
    def test_call_longest_comment(self):
        with patch('requests.get', return_value=COMMENTS_DATA):
            response = ApiProcessor().longest_comment()
        assert response['id'] == 3

    def test_call_longest_title(self):
        with patch('requests.get', return_value=POSTS_DATA):
            response = ApiProcessor().post_with_longest_title()
        assert response['id'] == 50
