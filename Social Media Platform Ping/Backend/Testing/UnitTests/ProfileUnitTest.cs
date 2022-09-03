using Microsoft.VisualStudio.TestTools.UnitTesting;
using ProfileService.Models;
using System;

namespace Testing
{
    [TestClass]
    public class ProfileUnitTest
    {
        Profile profile = new Profile { Id = 1, Birthday = DateTime.Now, Education = "education", Intro = "intro", isBlocked = false, isPublic = true, Job = "job", LivingCity = "LivingCity", LivingCountry = "LivingCountry", OriginCityy = "OriginCity", OriginCountry = "OriginCountry", Role = "user", Username = "username" };

        [TestMethod]
        public void ConstructorUnitTest()
        {
            //Arrange
            int id = 1;
            DateTime birthday = DateTime.Now;
            string education = "education";            
            string intro = "intro";
            bool isBlocked = false;
            bool isPublic = true;
            string job = "job";            
            string livingCity = "LivingCity";
            string livingCountry = "LivingCountry";
            string originCountry = "OriginCountry";
            string originCity = "OriginCity";
            string role = "user";
            string username = "username";

            //Act
            Profile actualProfile = new Profile { Id = id, Birthday = birthday, Education = education, Intro = intro, isBlocked = isBlocked, isPublic = isPublic, Job = job, LivingCity = livingCity, LivingCountry = livingCountry, OriginCityy = originCity, OriginCountry = originCountry, Role = role, Username = username};

            //Assert
            Assert.AreEqual(id, actualProfile.Id);
            Assert.AreEqual(birthday, actualProfile.Birthday);
            Assert.AreEqual(education, actualProfile.Education);           
            Assert.AreEqual(intro, actualProfile.Intro);
            Assert.AreEqual(job, actualProfile.Job);
            Assert.IsTrue(actualProfile.isPublic);
            Assert.IsFalse(actualProfile.isBlocked);
            Assert.AreEqual(livingCity, actualProfile.LivingCity);
            Assert.AreEqual(livingCountry, actualProfile.LivingCountry);
            Assert.AreEqual(originCountry, actualProfile.OriginCountry);
            Assert.AreEqual(originCity, actualProfile.OriginCityy);
            Assert.AreEqual(role, actualProfile.Role);
            Assert.AreEqual(username, actualProfile.Username);
        }


        [TestMethod]
        public void UpdateIdUnitTest()
        {
            //Arrange
            int id = 2;

            //Act
            profile.Id = id;

            //Assert
            Assert.AreEqual(id, profile.Id);            
        }


        [TestMethod]
        public void UpdateBirthdayUnitTest()
        {
            //Arrange
            DateTime birthday = DateTime.Now;

            //Act
            profile.Birthday = birthday;

            //Assert
            Assert.AreEqual(birthday, profile.Birthday);
        }


        [TestMethod]
        public void UpdateEducationUnitTest()
        {
            //Arrange
            string education = "newEducation";

            //Act
            profile.Education = education;

            //Assert
            Assert.AreEqual(education, profile.Education);
        }


        [TestMethod]
        public void UpdateIntroUnitTest()
        {
            //Arrange
            string intro = "newIntro";

            //Act
            profile.Intro = intro;

            //Assert
            Assert.AreEqual(intro, profile.Intro);
        }


        [TestMethod]
        public void UpdateIsBlockedUnitTest()
        {
            //Arrange
            bool isBlocked = true;
            
            //Act
            profile.isBlocked = isBlocked;

            //Assert
            Assert.IsTrue(profile.isBlocked);
        }


        [TestMethod]
        public void UpdateIsPublicUnitTest()
        {
            //Arrange
            bool isPublic = false;

            //Act
            profile.isPublic = isPublic;

            //Assert
            Assert.IsFalse(profile.isPublic);
        }


        [TestMethod]
        public void UpdateJobUnitTest()
        {
            //Arrange
            string job = "newJob";            

            //Act
            profile.Job = job;

            //Assert
            Assert.AreEqual(job, profile.Job);
        }


        [TestMethod]
        public void UpdateLivingCityUnitTest()
        {
            //Arrange
            string livingCity = "newLivingCity";

            //Act
            profile.LivingCity = livingCity;

            //Assert
            Assert.AreEqual(livingCity, profile.LivingCity);
        }


        [TestMethod]
        public void UpdateLivingCoutryUnitTest()
        {
            //Arrange
            string livingCountry = "newLivingCountry";
            
            //Act
            profile.LivingCountry = livingCountry;

            //Assert
            Assert.AreEqual(livingCountry, profile.LivingCountry);
        }


        [TestMethod]
        public void UpdateOriginCountryUnitTest()
        {
            //Arrange
            string originCountry = "newOriginCountry";

            //Act
            profile.OriginCountry = originCountry;

            //Assert
            Assert.AreEqual(originCountry, profile.OriginCountry);
        }

        [TestMethod]
        public void UpdateOriginCityUnitTest()
        {
            //Arrange
            string originCity = "newOriginCity";           

            //Act
            profile.OriginCityy = originCity;

            //Assert
            Assert.AreEqual(originCity, profile.OriginCityy);
        }


        [TestMethod]
        public void UpdateRoleUnitTest()
        {
            //Arrange
            string role = "admin";

            //Act
            profile.Role = role;

            //Assert
            Assert.AreEqual(role, profile.Role);
        }


        [TestMethod]
        public void UpdateUsernameUnitTest()
        {
            //Arrange
            string username = "newUsername";

            //Act
            profile.Username = username;

            //Assert
            Assert.AreEqual(username, profile.Username);
        }
    }
}
