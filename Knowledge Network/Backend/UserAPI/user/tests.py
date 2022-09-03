import unittest
import os
from django.contrib.auth.hashers import check_password, make_password
os.environ.setdefault("DJANGO_SETTINGS_MODULE", "config.settings")
import django
import sys
sys.path.append('../')
import config
django.setup()
from .models import User

# Unit Tests for User class


class UserTestCase(unittest.TestCase):

    #constructor unit test
    def test_constructor(self):
        user = User(first_name="Axl", last_name="Rose", email="a.rose@gmail.com", password="123456",
                    job_title="Data Scientist", role="User")

        self.assertEqual(user.first_name, "Axl")
        self.assertEqual(user.last_name, "Rose")
        self.assertEqual(user.email, "a.rose@gmail.com")       
        self.assertEqual(user.job_title, "Data Scientist")
        self.assertEqual(user.role, "User")

    #is_anonymous function unit test
    def test_is_anonymous(self):
        user = User(first_name="Axl", last_name="Rose", email="a.rose@gmail.com", password="123456",
                    job_title="Data Scientist", role="User")
        self.assertEqual(user.is_anonymous, False) 

    #is_active function unit test
    def test_is_active(self):
        user = User(first_name="Axl", last_name="Rose", email="a.rose@gmail.com", password="123456",
                    job_title="Data Scientist", role="User")
        self.assertEqual(user.is_active, True) 

    #is_authenticated function unit test
    def test_is_authenticated(self):
        user = User(first_name="Axl", last_name="Rose", email="a.rose@gmail.com", password="123456",
                    job_title="Data Scientist", role="User")
        self.assertEqual(user.is_authenticated, False)     

    #set_password function unit test
    def test_set_password(self):
        user = User(first_name="Axl", last_name="Rose", email="a.rose@gmail.com", password="old_password",
                    job_title="Data Scientist", role="User")
        user.set_password("new_password")     
        self.assertEqual(check_password("new_password",user.password), True)      
            
    #unsuccessful check_password function unit test
    def test_check_password_unsuccessful(self):
        user = User(first_name="Axl", last_name="Rose", email="a.rose@gmail.com", password="old_password",
                    job_title="Data Scientist", role="User")        
        self.assertFalse(user.check_password("new_password"))                 
   
    #successful check_password function unit test
    def test_check_password_successful(self):
        user = User(first_name="Axl", last_name="Rose", email="a.rose@gmail.com", password="7",
                    job_title="Data Scientist", role="User") 
        user.set_password("12345")
        result = user.check_password("12345")       
        self.assertTrue(result)  

    #__str__ unit test
    def test__str__(self):
        user = User(first_name="Axl", last_name="Rose", email="a.rose@gmail.com", password="123456",
                    job_title="Data Scientist", role="User")
        self.assertEqual(user.__str__(), "Axl Rose")  

    #change first_name unit test
    def test_first_name(self):
        user = User(first_name="Axl", last_name="Rose", email="a.rose@gmail.com", password="123456",
                    job_title="Data Scientist", role="User")
        user.first_name = "Steven"
        self.assertEqual(user.first_name, "Steven")

    #change last_name unit test
    def test_last_name(self):
        user = User(first_name="Axl", last_name="Rose", email="a.rose@gmail.com", password="123456",
                    job_title="Data Scientist", role="User")
        user.last_name = "Tyler"
        self.assertEqual(user.last_name, "Tyler") 

    #change email unit test
    def test_email(self):
        user = User(first_name="Axl", last_name="Rose", email="a.rose@gmail.com", password="123456",
                    job_title="Data Scientist", role="User")
        user.email = "s.tyler@gmail.com"
        self.assertEqual(user.email, "s.tyler@gmail.com")  

    #change job_title unit test
    def test_job_title(self):
        user = User(first_name="Axl", last_name="Rose", email="a.rose@gmail.com", password="123456",
                    job_title="Data Scientist", role="User")
        user.job_title = "Guitarist"
        self.assertEqual(user.job_title, "Guitarist") 

    #change role unit test
    def test_role(self):
        user = User(first_name="Axl", last_name="Rose", email="a.rose@gmail.com", password="123456",
                    job_title="Data Scientist", role="User")
        user.role = "Admin"
        self.assertEqual(user.role, "Admin")  

if __name__ == '__main__':
    unittest.main()