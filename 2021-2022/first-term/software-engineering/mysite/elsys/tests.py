import requests
from django.test import TestCase
from elsys.processors import api_processor

LONGEST_COMMENT = requests.get('https://jsonplaceholder.typicode.com/posts/1/comments').json()[2]
LONGEST_TITLE_POST = requests.get('https://jsonplaceholder.typicode.com/posts').json()[57]


class TestApiProcessor(TestCase):
    def setUp(self):
        pass

    def tearDown(self):
        pass

    def test_comment_is_longest(self):
        assert api_processor.ApiProcessor.longest_comment() == LONGEST_COMMENT

    def test_post_title_is_longest(self):
        assert api_processor.ApiProcessor.post_with_longest_title() == LONGEST_TITLE_POST

# from elsys.models import Car
# from datetime import datetime
#
# class TestCarModel(TestCase):
#     def setUp(self):
#         #called when test is started
#         Car.objects.create(color="red", made=datetime.now(), brand="audi", description = "red car", is_electric=False)
#         Car.objects.create(color="green", made=datetime.now(), brand="bmw", description = "red bmw")
#
#     def tearDown(self):
#         #called after tests were run
#         pass
#
#     def test_car_is_red(self):
#         assert Car.objects.get(brand="audi").color == "red"
#
#     def test_car_is_green(self):
#         assert Car.objects.get(brand="bmw").color == "green"
#
#     def test_car_sound(self):
#         assert Car.objects.get(brand="audi").sound_check() == "brum brum"
#
# from unittest.mock import patch

# class ApiHandler:
#    def call_api(self):
#        response = requests.get("url")
#        data = response['data']
#        return data['a'] + 1


# class TestC(TestCase):
#    @patch("requests.get")
#    def test_call_api(self, mocked_requests):
#        mocked_requests.return_value = {'data':{'a': 1}}
#        response = ApiHandler().call_api()
#        assert response == 2
