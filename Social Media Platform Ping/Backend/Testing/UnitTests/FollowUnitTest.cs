using FollowService.Models;
using Microsoft.VisualStudio.TestTools.UnitTesting;


namespace Testing.UnitTests
{
    [TestClass]
    public class FollowUnitTest
    {
        Follow follow = new Follow { Id = 1, Followed = "followed", Follower = "follower", isAccepted = true, isPending = false };

        [TestMethod]
        public void ConstructorUnitTest()
        {
            //Arrange
            int id = 1;
            string follower = "follower";
            string followed = "followed";
            bool isAccepted = true;
            bool isPending = false;

            //Act
            Follow actualFollow = new Follow { Id = id, Followed = followed, Follower = follower, isAccepted = isAccepted, isPending = isPending};

            //Assert
            Assert.AreEqual(id, actualFollow.Id);
            Assert.AreEqual(follower, actualFollow.Follower);
            Assert.AreEqual(followed, actualFollow.Followed);
            Assert.IsTrue(actualFollow.isAccepted);
            Assert.IsFalse(actualFollow.isPending);
        }


        [TestMethod]
        public void UpdateIdUnitTest()
        {
            //Arrange
            int id = 2;

            //Act
            follow.Id = id;

            //Assert
            Assert.AreEqual(id, follow.Id);            
        }


        [TestMethod]
        public void UpdateFollowerUnitTest()
        {
            //Arrange
            string follower = "newFollower";

            //Act
            follow.Follower = follower;

            //Assert
            Assert.AreEqual(follower, follow.Follower);
        }



        [TestMethod]
        public void UpdateFollowedUnitTest()
        {
            //Arrange
            string followed = "newFollowed";

            //Act
            follow.Followed = followed;

            //Assert
            Assert.AreEqual(followed, follow.Followed);
        }


        [TestMethod]
        public void UpdateIsAcceptedUnitTest()
        {
            //Arrange
            bool isAccepted = false;

            //Act
            follow.isAccepted = isAccepted;

            //Assert
            Assert.AreEqual(isAccepted, follow.isAccepted);
        }


        [TestMethod]
        public void UpdateIsPendingUnitTest()
        {
            //Arrange
            bool isPending = false;

            //Act
            follow.isPending = isPending;

            //Assert
            Assert.AreEqual(isPending, follow.isPending);
        }
    }
}
