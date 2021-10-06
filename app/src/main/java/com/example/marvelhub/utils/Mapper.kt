package com.example.marvelhub.utils

interface Mapper<In , Out> {
   fun from(input :In): Out
   fun to (output :Out):In
}