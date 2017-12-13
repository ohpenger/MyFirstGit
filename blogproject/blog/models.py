from django.db import models
from django.contrib.auth.models import User
from django.urls import reverse
# Create your models here.
class Category(models.Model):
    name=models.CharField(max_length=100)
class Tag(models.Model):
    name=models.CharField(max_length=100)
class Post(models.Model):
    title=models.CharField(max_length=70)
    body=models.TextField()
    created_time=models.DateField()
    modified_time=models.DateField()
    excerpt=models.CharField(max_length=200,blank=True)
    category =models.ForeignKey(Category)
    tags=models.ManyToManyField(Tag)
    author=models.ForeignKey(User,default=1)
    def __str__(self):
        return self.title
    def get_absolute_url(self):
        return reverse('blog:detail',kwargs={'pk':self.pk})

