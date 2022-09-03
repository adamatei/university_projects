using Microsoft.VisualStudio.TestTools.UnitTesting;
using PostService.Models;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;

namespace Testing
{
    [TestClass]
    public class PostUnitTest
    {
        Post post = new Post { Id = 1, CreatedBy = "username", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new Collection<Tag>() };

        [TestMethod]
        public void ConstructorUnitTest()
        {
            //Arrange
            int id = 1;
            string createdBy = "username";
            DateTime createdOn = DateTime.Now;
            string text = "This is a post!";
            Collection<Tag> tags = new Collection<Tag>();

            //Act
            Post actualPost = new Post { Id = id, CreatedBy = createdBy, CreatedOn = createdOn, Text = text, Tags = tags};

            //Assert
            Assert.AreEqual(id, actualPost.Id);
            Assert.AreEqual(createdBy, actualPost.CreatedBy);
            Assert.AreEqual(createdOn, actualPost.CreatedOn);
            Assert.AreEqual(text, actualPost.Text);
            CollectionAssert.AreEqual(tags, (System.Collections.ICollection)actualPost.Tags);
        }


        [TestMethod]
        public void UpdateIdUnitTest()
        {
            //Arrange
            int id = 2;

            //Act
            post.Id = id;

            //Assert
            Assert.AreEqual(id, post.Id);            
        }


        [TestMethod]
        public void UpdateCreatedByUnitTest()
        {
            //Arrange
            string createdBy = "newUsername";

            //Act
            post.CreatedBy = createdBy;

            //Assert
            Assert.AreEqual(createdBy, post.CreatedBy);
        }


        [TestMethod]
        public void UpdateCreatedOnUnitTest()
        {
            //Arrange
            DateTime createdOn = DateTime.Now;

            //Act
            post.CreatedOn = createdOn;

            //Assert
            Assert.AreEqual(createdOn, post.CreatedOn);
        }


        [TestMethod]
        public void UpdateTextUnitTest()
        {
            //Arrange
            string text = "This is a post!";

            //Act
            post.Text = text;

            //Assert
            Assert.AreEqual(text, post.Text);
        }



        [TestMethod]
        public void UpdateTagsUnitTest()
        {
            //Arrange
            Collection<Tag> tags = new Collection<Tag>();
            Tag tag = new Tag { Id = 1, Content = "fun", Type = "hashtag"};
            tags.Add(tag);

            //Act
            post.Tags = tags;

            //Assert
            Assert.AreEqual(tags, post.Tags);
        }
    }
}
