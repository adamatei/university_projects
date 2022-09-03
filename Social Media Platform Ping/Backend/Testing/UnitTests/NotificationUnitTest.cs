using Microsoft.VisualStudio.TestTools.UnitTesting;
using NotificationService.Models;
using System;


namespace Testing.UnitTests
{
    [TestClass]
    public class NotificationUnitTest
    {
        Notification notification = new Notification { Id = 1, Content = "This is a notification!", CreatedOn = DateTime.Now, For = "username" };

        [TestMethod]
        public void ConstructorUnitTest()
        {
            //Arrange
            int id = 1;
            string content = "This is a notification!";
            DateTime createdOn = DateTime.Now;
            string meantFor = "username";

            //Act
            Notification actualNotification = new Notification { Id = id, Content = content, CreatedOn = createdOn, For =  meantFor};

            //Assert
            Assert.AreEqual(id, actualNotification.Id);
            Assert.AreEqual(content, actualNotification.Content);
            Assert.AreEqual(createdOn, actualNotification.CreatedOn);
            Assert.AreEqual(meantFor, actualNotification.For);
        }

        [TestMethod]
        public void UpdateIdUnitTest()
        {
            //Arrange
            int id = 2;

            //Act
            notification.Id = id;

            //Assert
            Assert.AreEqual(id, notification.Id);           
        }


        [TestMethod]
        public void UpdateContentUnitTest()
        {
            //Arrange
            string content = "This is a new notification!";

            //Act
            notification.Content = content;

            //Assert
            Assert.AreEqual(content, notification.Content);
        }


        [TestMethod]
        public void UpdateCreatedOnUnitTest()
        {
            //Arrange
            DateTime createdOn = DateTime.Now;

            //Act
            notification.CreatedOn = createdOn;

            //Assert
            Assert.AreEqual(createdOn, notification.CreatedOn);
        }


        [TestMethod]
        public void UpdateForUnitTest()
        {
            //Arrange
            string meantFor = "username";

            //Act
            notification.For = meantFor;

            //Assert
            Assert.AreEqual(meantFor, notification.For);
        }
    }
}
