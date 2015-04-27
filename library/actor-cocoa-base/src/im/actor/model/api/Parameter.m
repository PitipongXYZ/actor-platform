//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: /Users/ex3ndr/Develop/actor-model/library/actor-cocoa-base/build/java/im/actor/model/api/Parameter.java
//

#line 1 "/Users/ex3ndr/Develop/actor-model/library/actor-cocoa-base/build/java/im/actor/model/api/Parameter.java"

#include "IOSClass.h"
#include "J2ObjC_source.h"
#include "im/actor/model/api/Parameter.h"
#include "im/actor/model/droidkit/bser/BserValues.h"
#include "im/actor/model/droidkit/bser/BserWriter.h"
#include "java/io/IOException.h"

@interface ImActorModelApiParameter () {
 @public
  NSString *key_;
  NSString *value_;
}
@end

J2OBJC_FIELD_SETTER(ImActorModelApiParameter, key_, NSString *)
J2OBJC_FIELD_SETTER(ImActorModelApiParameter, value_, NSString *)


#line 19
@implementation ImActorModelApiParameter


#line 24
- (instancetype)initWithNSString:(NSString *)key
                    withNSString:(NSString *)value {
  if (self = [super init]) {
    
#line 25
    self->key_ = key;
    
#line 26
    self->value_ = value;
  }
  return self;
}


#line 29
- (instancetype)init {
  return [super init];
}

- (NSString *)getKey {
  
#line 34
  return self->key_;
}


#line 37
- (NSString *)getValue {
  
#line 38
  return self->value_;
}


#line 42
- (void)parseWithBSBserValues:(BSBserValues *)values {
  self->key_ = [((BSBserValues *) nil_chk(values)) getStringWithInt:1];
  self->value_ = [values getStringWithInt:2];
}


#line 48
- (void)serializeWithBSBserWriter:(BSBserWriter *)writer {
  
#line 49
  if (self->key_ == nil) {
    @throw [[JavaIoIOException alloc] init];
  }
  [((BSBserWriter *) nil_chk(writer)) writeStringWithInt:1 withNSString:self->key_];
  if (self->value_ == nil) {
    @throw [[JavaIoIOException alloc] init];
  }
  [writer writeStringWithInt:2 withNSString:self->value_];
}

- (NSString *)description {
  NSString *res = @"struct Parameter{";
  res = JreStrcat("$C", res, '}');
  return res;
}

- (void)copyAllFieldsTo:(ImActorModelApiParameter *)other {
  [super copyAllFieldsTo:other];
  other->key_ = key_;
  other->value_ = value_;
}

@end

J2OBJC_CLASS_TYPE_LITERAL_SOURCE(ImActorModelApiParameter)
