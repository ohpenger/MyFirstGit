from django.db import models
import django.utils.timezone as timezone
# Create your models here.
class Massage(models.Model):
    massage=models.TextField('内容',null=True)
    name = models.CharField('名字',max_length=32, default='匿名')
    datatime=models.DateField('保存时间',default=timezone.now)
    def __str__(self):
        return self.name