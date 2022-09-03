using Microsoft.EntityFrameworkCore;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using ProfileService.Models;
using ProfileService.DataAccess;
using ProfileService.Services;
using System;
using System.Collections.Generic;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Testing.ManagerTests
{
    [TestClass]
    public class ProfileManagerTest
    {
        [TestMethod]
        public void GetProfilesTest_Successful()
        {
            //Arrange
            Profile profile1 = new Profile { Id = 1, Birthday = DateTime.Now, Education = "education1", Intro = "intro", isBlocked = false, isPublic = true, Job = "job", LivingCity = "LivingCity", LivingCountry = "LivingCountry", OriginCityy = "OriginCity", OriginCountry = "OriginCountry", Role = "user", Username = "username" };
            Profile profile2 = new Profile { Id = 2, Birthday = DateTime.Now, Education = "education2", Intro = "intro", isBlocked = false, isPublic = true, Job = "job", LivingCity = "LivingCity", LivingCountry = "LivingCountry", OriginCityy = "OriginCity", OriginCountry = "OriginCountry", Role = "user", Username = "username" };

            var data = new List<Profile>
            {
                profile1,
                profile2               
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Profile>>();
            mockSet.As<IDbAsyncEnumerable<Profile>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Profile>(data.GetEnumerator()));

            mockSet.As<IQueryable<Profile>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Profile>(data.Provider));

            mockSet.As<IQueryable<Profile>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Profile>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Profile>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Profiles).Returns(mockSet.Object);

            var mockEncryption = new Mock<EncrytionLogic>();
            mockEncryption.Setup(c => c.DecryptProfile(It.IsAny<Profile>())).Returns<Profile> (x => x);

            var service = new ProfileManager(mockContext.Object, mockEncryption.Object);

            //Act       
            List<Profile> result = service.GetProfiles();

            //Assert
            CollectionAssert.AreEqual(new List<Profile> { profile1, profile2}, result.ToArray());
        }


        [TestMethod]
        public void GetProfileByIdTest_Successful()
        {
            //Arrange
            Profile profile1 = new Profile { Id = 1, Birthday = DateTime.Now, Education = "education1", Intro = "intro", isBlocked = false, isPublic = true, Job = "job", LivingCity = "LivingCity", LivingCountry = "LivingCountry", OriginCityy = "OriginCity", OriginCountry = "OriginCountry", Role = "user", Username = "username" };
            Profile profile2 = new Profile { Id = 2, Birthday = DateTime.Now, Education = "education2", Intro = "intro", isBlocked = false, isPublic = true, Job = "job", LivingCity = "LivingCity", LivingCountry = "LivingCountry", OriginCityy = "OriginCity", OriginCountry = "OriginCountry", Role = "user", Username = "username" };

            var data = new List<Profile>
            {
                profile1,
                profile2
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Profile>>();
            mockSet.As<IDbAsyncEnumerable<Profile>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Profile>(data.GetEnumerator()));

            mockSet.As<IQueryable<Profile>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Profile>(data.Provider));

            mockSet.As<IQueryable<Profile>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Profile>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Profile>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Profiles).Returns(mockSet.Object);
            mockContext.Setup(c => c.Profiles.FindAsync(It.IsAny<int>())).Returns(new ValueTask<Profile>(profile1));

            var mockEncryption = new Mock<EncrytionLogic>();
            mockEncryption.Setup(c => c.DecryptProfile(It.IsAny<Profile>())).Returns<Profile>(x => x);

            var service = new ProfileManager(mockContext.Object, mockEncryption.Object);

            //Act       
            Profile result = service.GetProfileById(1).Result;

            //Assert
            Assert.AreEqual(profile1.Id, result.Id);
            Assert.AreEqual(profile1.Birthday, result.Birthday);
            Assert.AreEqual(profile1.Education, result.Education);           
            Assert.AreEqual(profile1.Intro, result.Intro);
            Assert.AreEqual(profile1.Job, result.Job);       
            Assert.IsTrue(result.isPublic);
            Assert.IsFalse(result.isBlocked);
            Assert.AreEqual(profile1.LivingCity, result.LivingCity);
            Assert.AreEqual(profile1.LivingCountry, result.LivingCountry);
            Assert.AreEqual(profile1.OriginCountry, result.OriginCountry);
            Assert.AreEqual(profile1.OriginCityy, result.OriginCityy);
            Assert.AreEqual(profile1.Role, result.Role);
            Assert.AreEqual(profile1.Username, result.Username);
        }


        [TestMethod]
        public void GetProfileByUsernameTest_Successful()
        {
            //Arrange
            Profile profile1 = new Profile { Id = 1, Birthday = DateTime.Now, Education = "education1", Intro = "intro", isBlocked = false, isPublic = true, Job = "job", LivingCity = "LivingCity", LivingCountry = "LivingCountry", OriginCityy = "OriginCity", OriginCountry = "OriginCountry", Role = "user", Username = "username" };
            Profile profile2 = new Profile { Id = 2, Birthday = DateTime.Now, Education = "education2", Intro = "intro", isBlocked = false, isPublic = true, Job = "job", LivingCity = "LivingCity", LivingCountry = "LivingCountry", OriginCityy = "OriginCity", OriginCountry = "OriginCountry", Role = "user", Username = "username2" };

            var data = new List<Profile>
            {
                profile1,
                profile2
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Profile>>();
            mockSet.As<IDbAsyncEnumerable<Profile>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Profile>(data.GetEnumerator()));

            mockSet.As<IQueryable<Profile>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Profile>(data.Provider));

            mockSet.As<IQueryable<Profile>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Profile>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Profile>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Profiles).Returns(mockSet.Object);

            var mockEncryption = new Mock<EncrytionLogic>();
            mockEncryption.Setup(c => c.DecryptProfile(It.IsAny<Profile>())).Returns<Profile>(x => x);

            var service = new ProfileManager(mockContext.Object, mockEncryption.Object);

            //Act       
            Profile result = service.GetProfileByUsername("username");

            //Assert
            Assert.AreEqual(profile1.Id, result.Id);
            Assert.AreEqual(profile1.Birthday, result.Birthday);
            Assert.AreEqual(profile1.Education, result.Education);   
            Assert.AreEqual(profile1.Intro, result.Intro);
            Assert.AreEqual(profile1.Job, result.Job);            
            Assert.IsTrue(result.isPublic);
            Assert.IsFalse(result.isBlocked);
            Assert.AreEqual(profile1.LivingCity, result.LivingCity);
            Assert.AreEqual(profile1.LivingCountry, result.LivingCountry);
            Assert.AreEqual(profile1.OriginCountry, result.OriginCountry);
            Assert.AreEqual(profile1.OriginCityy, result.OriginCityy);
            Assert.AreEqual(profile1.Role, result.Role);
            Assert.AreEqual(profile1.Username, result.Username);
        }


        [TestMethod]
        public void GetProfileByUsernameTest_Unsuccessful()
        {
            //Arrange
            Profile profile1 = new Profile { Id = 1, Birthday = DateTime.Now, Education = "education1", Intro = "intro", isBlocked = false, isPublic = true, Job = "job", LivingCity = "LivingCity", LivingCountry = "LivingCountry", OriginCityy = "OriginCity", OriginCountry = "OriginCountry", Role = "user", Username = "username" };
            Profile profile2 = new Profile { Id = 2, Birthday = DateTime.Now, Education = "education2", Intro = "intro", isBlocked = false, isPublic = true, Job = "job", LivingCity = "LivingCity", LivingCountry = "LivingCountry", OriginCityy = "OriginCity", OriginCountry = "OriginCountry", Role = "user", Username = "username2" };

            var data = new List<Profile>
            {
                profile1,
                profile2
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Profile>>();
            mockSet.As<IDbAsyncEnumerable<Profile>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Profile>(data.GetEnumerator()));

            mockSet.As<IQueryable<Profile>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Profile>(data.Provider));

            mockSet.As<IQueryable<Profile>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Profile>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Profile>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Profiles).Returns(mockSet.Object);

            var mockEncryption = new Mock<EncrytionLogic>();
            mockEncryption.Setup(c => c.DecryptProfile(It.IsAny<Profile>())).Returns<Profile>(x => x);

            var service = new ProfileManager(mockContext.Object, mockEncryption.Object);

            //Act       
            Profile result = service.GetProfileByUsername("fakeUsername");

            //Assert
            Assert.IsNull(result);
        }


        [TestMethod]
        public void DeleteProfileTest_Successful()
        {
            //Arrange
            Profile profile1 = new Profile { Id = 1, Birthday = DateTime.Now, Education = "education1", Intro = "intro", isBlocked = false, isPublic = true, Job = "job", LivingCity = "LivingCity", LivingCountry = "LivingCountry", OriginCityy = "OriginCity", OriginCountry = "OriginCountry", Role = "user", Username = "username" };
            Profile profile2 = new Profile { Id = 2, Birthday = DateTime.Now, Education = "education2", Intro = "intro", isBlocked = false, isPublic = true, Job = "job", LivingCity = "LivingCity", LivingCountry = "LivingCountry", OriginCityy = "OriginCity", OriginCountry = "OriginCountry", Role = "user", Username = "username2" };

            var data = new List<Profile>
            {
                profile1,
                profile2
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Profile>>();
            mockSet.As<IDbAsyncEnumerable<Profile>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Profile>(data.GetEnumerator()));

            mockSet.As<IQueryable<Profile>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Profile>(data.Provider));

            mockSet.As<IQueryable<Profile>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Profile>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Profile>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Profiles).Returns(mockSet.Object);

            var mockEncryption = new Mock<EncrytionLogic>();
            
            var service = new ProfileManager(mockContext.Object, mockEncryption.Object);

            //Act       
            bool result = service.DeleteProfile(1).Result;

            //Assert
            Assert.IsTrue(result);
        }



        [TestMethod]
        public void DeleteProfileTest_Unsuccessful()
        {
            //Arrange
            Profile profile1 = new Profile { Id = 1, Birthday = DateTime.Now, Education = "education1", Intro = "intro", isBlocked = false, isPublic = true, Job = "job", LivingCity = "LivingCity", LivingCountry = "LivingCountry", OriginCityy = "OriginCity", OriginCountry = "OriginCountry", Role = "user", Username = "username" };
            Profile profile2 = new Profile { Id = 2, Birthday = DateTime.Now, Education = "education2", Intro = "intro", isBlocked = false, isPublic = true, Job = "job", LivingCity = "LivingCity", LivingCountry = "LivingCountry", OriginCityy = "OriginCity", OriginCountry = "OriginCountry", Role = "user", Username = "username2" };

            var data = new List<Profile>
            {
                profile1,
                profile2
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Profile>>();
            mockSet.As<IDbAsyncEnumerable<Profile>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Profile>(data.GetEnumerator()));

            mockSet.As<IQueryable<Profile>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Profile>(data.Provider));

            mockSet.As<IQueryable<Profile>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Profile>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Profile>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Profiles).Returns(mockSet.Object);

            var mockEncryption = new Mock<EncrytionLogic>();
            
            var service = new ProfileManager(mockContext.Object, mockEncryption.Object);

            //Act       
            bool result = service.DeleteProfile(4).Result;

            //Assert
            Assert.IsFalse(result);
        }



        [TestMethod]
        public void UpdateProfileTest_Successful()
        {
            //Arrange
            Profile profile1 = new Profile { Id = 1, Birthday = DateTime.Now, Education = "education1", Intro = "intro", isBlocked = false, isPublic = true, Job = "job", LivingCity = "LivingCity", LivingCountry = "LivingCountry", OriginCityy = "OriginCity", OriginCountry = "OriginCountry", Role = "user", Username = "username" };
            Profile profile2 = new Profile { Id = 2, Birthday = DateTime.Now, Education = "education2", Intro = "intro", isBlocked = false, isPublic = true, Job = "job", LivingCity = "LivingCity", LivingCountry = "LivingCountry", OriginCityy = "OriginCity", OriginCountry = "OriginCountry", Role = "user", Username = "username2" };

            var data = new List<Profile>
            {
                profile1,
                profile2
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Profile>>();
            mockSet.As<IDbAsyncEnumerable<Profile>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Profile>(data.GetEnumerator()));

            mockSet.As<IQueryable<Profile>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Profile>(data.Provider));

            mockSet.As<IQueryable<Profile>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Profile>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Profile>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Profiles).Returns(mockSet.Object);
            Profile updateProfile = new Profile { Id = 1, Birthday = DateTime.Now, Education = "newEducation1", Intro = "intro", isBlocked = false, isPublic = true, Job = "newJob", LivingCity = "LivingCity", LivingCountry = "LivingCountry", OriginCityy = "OriginCity", OriginCountry = "OriginCountry", Role = "user", Username = "username" };

            var mockEncryption = new Mock<EncrytionLogic>();
            mockEncryption.Setup(c => c.EncryptTripleDES(It.IsAny<string>())).Returns<string>(x => x);

            var service = new ProfileManager(mockContext.Object, mockEncryption.Object);

            //Act       
            bool result = service.UpdateProfile(updateProfile).Result;

            //Assert
            Assert.IsTrue(result);
        }



        [TestMethod]
        public void UpdateProfileTest_Unsuccessful()
        {
            //Arrange
            Profile profile1 = new Profile { Id = 1, Birthday = DateTime.Now, Education = "education1", Intro = "intro", isBlocked = false, isPublic = true, Job = "job", LivingCity = "LivingCity", LivingCountry = "LivingCountry", OriginCityy = "OriginCity", OriginCountry = "OriginCountry", Role = "user", Username = "username" };
            Profile profile2 = new Profile { Id = 2, Birthday = DateTime.Now, Education = "education2", Intro = "intro", isBlocked = false, isPublic = true, Job = "job", LivingCity = "LivingCity", LivingCountry = "LivingCountry", OriginCityy = "OriginCity", OriginCountry = "OriginCountry", Role = "user", Username = "username2" };

            var data = new List<Profile>
            {
                profile1,
                profile2
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Profile>>();
            mockSet.As<IDbAsyncEnumerable<Profile>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Profile>(data.GetEnumerator()));

            mockSet.As<IQueryable<Profile>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Profile>(data.Provider));

            mockSet.As<IQueryable<Profile>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Profile>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Profile>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Profiles).Returns(mockSet.Object);
            Profile updateProfile = new Profile { Id = 4, Birthday = DateTime.Now, Education = "newEducation1", Intro = "intro", isBlocked = false, isPublic = true, Job = "newJob", LivingCity = "LivingCity", LivingCountry = "LivingCountry", OriginCityy = "OriginCity", OriginCountry = "OriginCountry", Role = "user", Username = "username" };

            var mockEncryption = new Mock<EncrytionLogic>();
            mockEncryption.Setup(c => c.EncryptTripleDES(It.IsAny<string>())).Returns<string>(x => x);

            var service = new ProfileManager(mockContext.Object, mockEncryption.Object);

            //Act       
            bool result = service.UpdateProfile(updateProfile).Result;

            //Assert
            Assert.IsFalse(result);
        }


        [TestMethod]
        public void CreateProfileTest_Successful()
        {
            //Arrange
            Profile profile1 = new Profile { Id = 1, Birthday = DateTime.Now, Education = "education1", Intro = "intro", isBlocked = false, isPublic = true, Job = "job", LivingCity = "LivingCity", LivingCountry = "LivingCountry", OriginCityy = "OriginCity", OriginCountry = "OriginCountry", Role = "user", Username = "username" };
            Profile profile2 = new Profile { Id = 2, Birthday = DateTime.Now, Education = "education2", Intro = "intro", isBlocked = false, isPublic = true, Job = "job", LivingCity = "LivingCity", LivingCountry = "LivingCountry", OriginCityy = "OriginCity", OriginCountry = "OriginCountry", Role = "user", Username = "username2" };

            var data = new List<Profile>
            {
                profile1,
                profile2
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Profile>>();
            mockSet.As<IDbAsyncEnumerable<Profile>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Profile>(data.GetEnumerator()));

            mockSet.As<IQueryable<Profile>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Profile>(data.Provider));

            mockSet.As<IQueryable<Profile>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Profile>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Profile>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Profiles).Returns(mockSet.Object);
            Profile newProfile = new Profile { Id = 3, Birthday = DateTime.Now, Education = "newEducation1", Intro = "intro", isBlocked = false, isPublic = true, Job = "newJob", LivingCity = "LivingCity", LivingCountry = "LivingCountry", OriginCityy = "OriginCity", OriginCountry = "OriginCountry", Role = "user", Username = "newUsername" };

            var mockEncryption = new Mock<EncrytionLogic>();
            mockEncryption.Setup(e => e.EncryptTripleDES(It.IsAny<string>())).Returns((string myval) => { return myval; });

            var service = new ProfileManager(mockContext.Object, mockEncryption.Object);

            //Act       
            Profile result = service.CreateProfile(newProfile).Result;

            //Assert
            Assert.AreEqual(newProfile.Id, result.Id);
            Assert.AreEqual(newProfile.Birthday, result.Birthday);
            Assert.AreEqual(newProfile.Education, result.Education);            
            Assert.AreEqual(newProfile.Intro, result.Intro);
            Assert.AreEqual(newProfile.Job, result.Job);            
            Assert.IsTrue(result.isPublic);
            Assert.IsFalse(result.isBlocked);
            Assert.AreEqual(newProfile.LivingCity, result.LivingCity);
            Assert.AreEqual(newProfile.LivingCountry, result.LivingCountry);
            Assert.AreEqual(newProfile.OriginCountry, result.OriginCountry);
            Assert.AreEqual(newProfile.OriginCityy, result.OriginCityy);
            Assert.AreEqual(newProfile.Role, result.Role);
            Assert.AreEqual(newProfile.Username, result.Username);
        }


        [TestMethod]
        public void CreateProfileTest_Unsuccessful()
        {
            //Arrange
            Profile profile1 = new Profile { Id = 1, Birthday = DateTime.Now, Education = "education1", Intro = "intro", isBlocked = false, isPublic = true, Job = "job", LivingCity = "LivingCity", LivingCountry = "LivingCountry", OriginCityy = "OriginCity", OriginCountry = "OriginCountry", Role = "user", Username = "username" };
            Profile profile2 = new Profile { Id = 2, Birthday = DateTime.Now, Education = "education2", Intro = "intro", isBlocked = false, isPublic = true, Job = "job", LivingCity = "LivingCity", LivingCountry = "LivingCountry", OriginCityy = "OriginCity", OriginCountry = "OriginCountry", Role = "user", Username = "username2" };

            var data = new List<Profile>
            {
                profile1,
                profile2
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Profile>>();
            mockSet.As<IDbAsyncEnumerable<Profile>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Profile>(data.GetEnumerator()));

            mockSet.As<IQueryable<Profile>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Profile>(data.Provider));

            mockSet.As<IQueryable<Profile>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Profile>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Profile>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Profiles).Returns(mockSet.Object);
            Profile newProfile = new Profile { Id = 3, Birthday = DateTime.Now, Education = "newEducation1", Intro = "intro", isBlocked = false, isPublic = true, Job = "newJob", LivingCity = "LivingCity", LivingCountry = "LivingCountry", OriginCityy = "OriginCity", OriginCountry = "OriginCountry", Role = "user", Username = "username" };

            var mockEncryption = new Mock<EncrytionLogic>();
            mockEncryption.Setup(c => c.EncryptTripleDES(It.IsAny<string>())).Returns<string>(x => x);

            var service = new ProfileManager(mockContext.Object, mockEncryption.Object);

            //Act       
            Profile result = service.CreateProfile(newProfile).Result;

            //Assert
            Assert.IsNull(result);
        }
    }
}
