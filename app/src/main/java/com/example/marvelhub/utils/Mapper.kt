package com.example.marvelhub.utils

interface Mapper<In , Out> {
   fun from(input :In): Out
   fun to (input :Out):In
}