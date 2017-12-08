from django.contrib import admin
from massageboard.models import Massage
# Register your models here.
@admin.register(Massage)
class MassageAdmin(admin.ModelAdmin):
    list_display = ('name','massage','datatime')

