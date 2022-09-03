using NotificationService.DataAccess;
using NotificationService.Managers;
using NotificationService.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Data.Entity.Infrastructure;

namespace Testing.ManagerTests
{
    [TestClass]
    public class NotificationManagerTest
    {
        [TestMethod]
        public void GetNotificationByIdTest_Successful()
        {
            //Arrange
            Notification notification1 = new Notification { Id = 1, Content = "This is the first notification!", CreatedOn = System.DateTime.Now, For = "username1" };        
            Notification notification2 = new Notification { Id = 2, Content = "This is the second notification!", CreatedOn = System.DateTime.Now, For = "username2" };
            Notification notification3 = new Notification { Id = 3, Content = "This is the third notification!", CreatedOn = System.DateTime.Now, For = "username3" };
            
            var data = new List<Notification>
            {
                notification1,
                notification2,
                notification3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Notification>>();
            mockSet.As<IDbAsyncEnumerable<Notification>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Notification>(data.GetEnumerator()));

            mockSet.As<IQueryable<Notification>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Notification>(data.Provider));

            mockSet.As<IQueryable<Notification>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Notification>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Notification>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Notifications).Returns(mockSet.Object);
            mockContext.Setup(c => c.Notifications.FindAsync(It.IsAny<int>())).Returns(new ValueTask<Notification>(notification1));

            var service = new NotificationManager(mockContext.Object);

            //Act       
            Notification result = service.GetNotificationById(1).Result;

            //Assert
            Assert.AreEqual(notification1.Id, result.Id);
            Assert.AreEqual(notification1.Content, result.Content);
            Assert.AreEqual(notification1.CreatedOn, result.CreatedOn);
            Assert.AreEqual(notification1.For, result.For);          
        }


        [TestMethod]
        public void GetNotificationByIdTest_Unsuccessful()
        {
            //Arrange
            Notification notification1 = new Notification { Id = 1, Content = "This is the first notification!", CreatedOn = System.DateTime.Now, For = "username1" };
            Notification notification2 = new Notification { Id = 2, Content = "This is the second notification!", CreatedOn = System.DateTime.Now, For = "username2" };
            Notification notification3 = new Notification { Id = 3, Content = "This is the third notification!", CreatedOn = System.DateTime.Now, For = "username3" };

            var data = new List<Notification>
            {
                notification1,
                notification2,
                notification3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Notification>>();
            mockSet.As<IDbAsyncEnumerable<Notification>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Notification>(data.GetEnumerator()));

            mockSet.As<IQueryable<Notification>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Notification>(data.Provider));

            mockSet.As<IQueryable<Notification>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Notification>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Notification>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Notifications).Returns(mockSet.Object);
            mockContext.Setup(c => c.Notifications.FindAsync(It.IsAny<int>())).Returns(null);

            var service = new NotificationManager(mockContext.Object);

            //Act       
            Notification result = service.GetNotificationById(8).Result;            

            //Assert
            Assert.IsNull(result);
        }


        [TestMethod]
        public void GetNotificationsForUserTest_Successful()
        {
            //Arrange
            Notification notification1 = new Notification { Id = 1, Content = "This is the first notification!", CreatedOn = System.DateTime.Now, For = "username1" };
            Notification notification2 = new Notification { Id = 2, Content = "This is the second notification!", CreatedOn = System.DateTime.Now, For = "username2" };
            Notification notification3 = new Notification { Id = 3, Content = "This is the third notification!", CreatedOn = System.DateTime.Now, For = "username3" };

            var data = new List<Notification>
            {
                notification1,
                notification2,
                notification3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Notification>>();
            mockSet.As<IDbAsyncEnumerable<Notification>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Notification>(data.GetEnumerator()));

            mockSet.As<IQueryable<Notification>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Notification>(data.Provider));

            mockSet.As<IQueryable<Notification>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Notification>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Notification>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Notifications).Returns(mockSet.Object);            

            var service = new NotificationManager(mockContext.Object);

            //Act       
            List<Notification> result = service.GetNotificationsForUser("username1");

            //Assert
            CollectionAssert.AreEqual(new List<Notification> { notification1}, result);
        }


        [TestMethod]
        public void GetNotificationsForUserTest_Unsuccessful()
        {
            //Arrange
            Notification notification1 = new Notification { Id = 1, Content = "This is the first notification!", CreatedOn = System.DateTime.Now, For = "username1" };
            Notification notification2 = new Notification { Id = 2, Content = "This is the second notification!", CreatedOn = System.DateTime.Now, For = "username2" };
            Notification notification3 = new Notification { Id = 3, Content = "This is the third notification!", CreatedOn = System.DateTime.Now, For = "username3" };

            var data = new List<Notification>
            {
                notification1,
                notification2,
                notification3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Notification>>();
            mockSet.As<IDbAsyncEnumerable<Notification>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Notification>(data.GetEnumerator()));

            mockSet.As<IQueryable<Notification>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Notification>(data.Provider));

            mockSet.As<IQueryable<Notification>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Notification>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Notification>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Notifications).Returns(mockSet.Object);

            var service = new NotificationManager(mockContext.Object);

            //Act       
            List<Notification> result = service.GetNotificationsForUser("username100");

            //Assert
            CollectionAssert.AreEqual(new List<Notification>(), result);
        }


        [TestMethod]
        public void DeleteNotificationTest_Successful()
        {
            //Arrange
            Notification notification1 = new Notification { Id = 1, Content = "This is the first notification!", CreatedOn = System.DateTime.Now, For = "username1" };
            Notification notification2 = new Notification { Id = 2, Content = "This is the second notification!", CreatedOn = System.DateTime.Now, For = "username2" };
            Notification notification3 = new Notification { Id = 3, Content = "This is the third notification!", CreatedOn = System.DateTime.Now, For = "username3" };

            var data = new List<Notification>
            {
                notification1,
                notification2,
                notification3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Notification>>();
            mockSet.As<IDbAsyncEnumerable<Notification>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Notification>(data.GetEnumerator()));

            mockSet.As<IQueryable<Notification>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Notification>(data.Provider));

            mockSet.As<IQueryable<Notification>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Notification>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Notification>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Notifications).Returns(mockSet.Object);

            var service = new NotificationManager(mockContext.Object);

            //Act       
            bool result = service.DeleteNotification(1).Result;

            //Assert
            Assert.IsTrue(result);
        }


        [TestMethod]
        public void DeleteNotificationTest_Unsuccessful()
        {
            //Arrange
            Notification notification1 = new Notification { Id = 1, Content = "This is the first notification!", CreatedOn = System.DateTime.Now, For = "username1" };
            Notification notification2 = new Notification { Id = 2, Content = "This is the second notification!", CreatedOn = System.DateTime.Now, For = "username2" };
            Notification notification3 = new Notification { Id = 3, Content = "This is the third notification!", CreatedOn = System.DateTime.Now, For = "username3" };

            var data = new List<Notification>
            {
                notification1,
                notification2,
                notification3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Notification>>();
            mockSet.As<IDbAsyncEnumerable<Notification>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Notification>(data.GetEnumerator()));

            mockSet.As<IQueryable<Notification>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Notification>(data.Provider));

            mockSet.As<IQueryable<Notification>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Notification>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Notification>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Notifications).Returns(mockSet.Object);

            var service = new NotificationManager(mockContext.Object);

            //Act       
            bool result = service.DeleteNotification(100).Result;

            //Assert
            Assert.IsFalse(result);
        }


        [TestMethod]
        public void CreateNotificationTest_Successful()
        {
            //Arrange
            Notification notification1 = new Notification { Id = 1, Content = "This is the first notification!", CreatedOn = System.DateTime.Now, For = "username1" };
            Notification notification2 = new Notification { Id = 2, Content = "This is the second notification!", CreatedOn = System.DateTime.Now, For = "username2" };
            Notification notification3 = new Notification { Id = 3, Content = "This is the third notification!", CreatedOn = System.DateTime.Now, For = "username3" };

            var data = new List<Notification>
            {
                notification1,
                notification2,
                notification3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Notification>>();
            mockSet.As<IDbAsyncEnumerable<Notification>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Notification>(data.GetEnumerator()));

            mockSet.As<IQueryable<Notification>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Notification>(data.Provider));

            mockSet.As<IQueryable<Notification>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Notification>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Notification>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Notifications).Returns(mockSet.Object);
            Notification newNotification = new Notification { Id = 4, Content = "This is the new notification!", CreatedOn = System.DateTime.Now, For = "username4" };
            var service = new NotificationManager(mockContext.Object);

            //Act       
            Notification result = service.CreateNotification(newNotification).Result;

            //Assert
            Assert.AreEqual(newNotification.Id, result.Id);
            Assert.AreEqual(newNotification.Content, result.Content);
            Assert.AreEqual(newNotification.CreatedOn, result.CreatedOn);
            Assert.AreEqual(newNotification.For, result.For);
        }


        [TestMethod]
        public void CreateNotificationTest_Unsuccessful()
        {
            //Arrange
            Notification notification1 = new Notification { Id = 1, Content = "This is the first notification!", CreatedOn = System.DateTime.Now, For = "username1" };
            Notification notification2 = new Notification { Id = 2, Content = "This is the second notification!", CreatedOn = System.DateTime.Now, For = "username2" };
            Notification notification3 = new Notification { Id = 3, Content = "This is the third notification!", CreatedOn = System.DateTime.Now, For = "username3" };

            var data = new List<Notification>
            {
                notification1,
                notification2,
                notification3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Notification>>();
            mockSet.As<IDbAsyncEnumerable<Notification>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Notification>(data.GetEnumerator()));

            mockSet.As<IQueryable<Notification>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Notification>(data.Provider));

            mockSet.As<IQueryable<Notification>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Notification>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Notification>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Notifications).Returns(mockSet.Object);
            Notification newNotification = new Notification { Id = 1, Content = "This is the new notification!", CreatedOn = System.DateTime.Now, For = "username4" };
            var service = new NotificationManager(mockContext.Object);

            //Act       
            Notification result = service.CreateNotification(newNotification).Result;

            //Assert
            Assert.IsNull(result);
        }


        [TestMethod]
        public void UpdateNotificationTest_Successful()
        {
            //Arrange
            Notification notification1 = new Notification { Id = 1, Content = "This is the first notification!", CreatedOn = System.DateTime.Now, For = "username1" };
            Notification notification2 = new Notification { Id = 2, Content = "This is the second notification!", CreatedOn = System.DateTime.Now, For = "username2" };
            Notification notification3 = new Notification { Id = 3, Content = "This is the third notification!", CreatedOn = System.DateTime.Now, For = "username3" };

            var data = new List<Notification>
            {
                notification1,
                notification2,
                notification3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Notification>>();
            mockSet.As<IDbAsyncEnumerable<Notification>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Notification>(data.GetEnumerator()));

            mockSet.As<IQueryable<Notification>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Notification>(data.Provider));

            mockSet.As<IQueryable<Notification>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Notification>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Notification>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Notifications).Returns(mockSet.Object);
            Notification updateNotification = new Notification { Id = 1, Content = "This is the new notification!", CreatedOn = System.DateTime.Now, For = "username4" };
            var service = new NotificationManager(mockContext.Object);

            //Act       
            Notification result = service.UpdateNotification(updateNotification).Result;

            //Assert
            Assert.AreEqual(updateNotification.Id, result.Id);
            Assert.AreEqual(updateNotification.Content, result.Content);          
            Assert.AreEqual(updateNotification.For, result.For);
        }


        [TestMethod]
        public void UpdateNotificationTest_Unsuccessful()
        {
            //Arrange
            Notification notification1 = new Notification { Id = 1, Content = "This is the first notification!", CreatedOn = System.DateTime.Now, For = "username1" };
            Notification notification2 = new Notification { Id = 2, Content = "This is the second notification!", CreatedOn = System.DateTime.Now, For = "username2" };
            Notification notification3 = new Notification { Id = 3, Content = "This is the third notification!", CreatedOn = System.DateTime.Now, For = "username3" };

            var data = new List<Notification>
            {
                notification1,
                notification2,
                notification3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Notification>>();
            mockSet.As<IDbAsyncEnumerable<Notification>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Notification>(data.GetEnumerator()));

            mockSet.As<IQueryable<Notification>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Notification>(data.Provider));

            mockSet.As<IQueryable<Notification>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Notification>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Notification>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Notifications).Returns(mockSet.Object);
            Notification updateNotification = new Notification { Id = 4, Content = "This is the new notification!", CreatedOn = System.DateTime.Now, For = "username4" };
            var service = new NotificationManager(mockContext.Object);

            //Act       
            Notification result = service.UpdateNotification(updateNotification).Result;

            //Assert
            Assert.IsNull(result);
        }
    }
}
