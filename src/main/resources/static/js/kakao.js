/**
 * Kakao SDK for JavaScript - v1.40.14
 *
 * Copyright 2017 Kakao Corp.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 * OSS Notice | KakaoSDK-Javascript
 *
 * This application is Copyright © Kakao Corp. All rights reserved.
 * The following sets forth attribution notices for third party software that may be contained in this application.
 * If you have any questions or concerns, please contact us at opensource@kakaocorp.com
 *
 *
 * crypto-js
 *
 * https://github.com/brix/crypto-js
 *
 * Copyright 2009-2013 Jeff Mott
 * Copyright 2013-2016 Evan Vosberg
 *
 * MIT License
 *
 *
 * easyXDM
 *
 * https://github.com/oyvindkinsey/easyXDM/
 *
 * Copyright 2009-2011 Øyvind Sean Kinsey, oyvind@kinsey.no
 *
 * MIT License
 *
 *
 * ES6-Promise
 *
 * https://github.com/stefanpenner/es6-promise
 *
 * Copyright 2014 Yehuda Katz, Tom Dale, Stefan Penner and contributors
 *
 * MIT License
 *
 *
 * Kakao Web2App Library
 *
 * https://github.com/kakao/web2app
 *
 * Copyright 2015 Kakao Corp. http://www.kakaocorp.com
 *
 * MIT License
 *
 *
 * lodash
 *
 * https://github.com/lodash/lodash
 *
 * Copyright JS Foundation and other contributors
 *
 * MIT License
 *
 *
 * ua_parser
 *
 * https://github.com/html5crew/ua_parser
 *
 * Copyright HTML5 Tech. Team in Daum Communications Corp.
 *
 * MIT License
 *
 *
 * ``````````
 * MIT License
 *
 * Copyright (c)
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * 'Software'), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * ``````````
 */

(function (global, factory) {
  typeof exports === 'object' && typeof module !== 'undefined' ? factory(exports) :
  typeof define === 'function' && define.amd ? define(['exports'], factory) :
  (global = typeof globalThis !== 'undefined' ? globalThis : global || self, factory(global.Kakao = global.Kakao || {}));
}(this, (function (exports) { 'use strict';

  var freeGlobal = typeof global == 'object' && global && global.Object === Object && global;

  var freeSelf = typeof self == 'object' && self && self.Object === Object && self;
  var root = freeGlobal || freeSelf || Function('return this')();

  var Symbol$1 = root.Symbol;

  var objectProto$a = Object.prototype;
  var hasOwnProperty$8 = objectProto$a.hasOwnProperty;
  var nativeObjectToString$1 = objectProto$a.toString;
  var symToStringTag$1 = Symbol$1 ? Symbol$1.toStringTag : undefined;
  function getRawTag(value) {
    var isOwn = hasOwnProperty$8.call(value, symToStringTag$1),
        tag = value[symToStringTag$1];
    try {
      value[symToStringTag$1] = undefined;
      var unmasked = true;
    } catch (e) {}
    var result = nativeObjectToString$1.call(value);
    if (unmasked) {
      if (isOwn) {
        value[symToStringTag$1] = tag;
      } else {
        delete value[symToStringTag$1];
      }
    }
    return result;
  }

  var objectProto$9 = Object.prototype;
  var nativeObjectToString = objectProto$9.toString;
  function objectToString(value) {
    return nativeObjectToString.call(value);
  }

  var nullTag = '[object Null]',
      undefinedTag = '[object Undefined]';
  var symToStringTag = Symbol$1 ? Symbol$1.toStringTag : undefined;
  function baseGetTag(value) {
    if (value == null) {
      return value === undefined ? undefinedTag : nullTag;
    }
    return symToStringTag && symToStringTag in Object(value) ? getRawTag(value) : objectToString(value);
  }

  function isObjectLike(value) {
    return value != null && typeof value == 'object';
  }

  var symbolTag = '[object Symbol]';
  function isSymbol(value) {
    return typeof value == 'symbol' || isObjectLike(value) && baseGetTag(value) == symbolTag;
  }

  var isArray = Array.isArray;

  var reWhitespace = /\s/;
  function trimmedEndIndex(string) {
    var index = string.length;
    while (index-- && reWhitespace.test(string.charAt(index))) {}
    return index;
  }

  var reTrimStart = /^\s+/;
  function baseTrim(string) {
    return string ? string.slice(0, trimmedEndIndex(string) + 1).replace(reTrimStart, '') : string;
  }

  function isObject(value) {
    var type = typeof value;
    return value != null && (type == 'object' || type == 'function');
  }

  var NAN = 0 / 0;
  var reIsBadHex = /^[-+]0x[0-9a-f]+$/i;
  var reIsBinary = /^0b[01]+$/i;
  var reIsOctal = /^0o[0-7]+$/i;
  var freeParseInt = parseInt;
  function toNumber(value) {
    if (typeof value == 'number') {
      return value;
    }
    if (isSymbol(value)) {
      return NAN;
    }
    if (isObject(value)) {
      var other = typeof value.valueOf == 'function' ? value.valueOf() : value;
      value = isObject(other) ? other + '' : other;
    }
    if (typeof value != 'string') {
      return value === 0 ? value : +value;
    }
    value = baseTrim(value);
    var isBinary = reIsBinary.test(value);
    return isBinary || reIsOctal.test(value) ? freeParseInt(value.slice(2), isBinary ? 2 : 8) : reIsBadHex.test(value) ? NAN : +value;
  }

  var INFINITY = 1 / 0,
      MAX_INTEGER = 1.7976931348623157e+308;
  function toFinite(value) {
    if (!value) {
      return value === 0 ? value : 0;
    }
    value = toNumber(value);
    if (value === INFINITY || value === -INFINITY) {
      var sign = value < 0 ? -1 : 1;
      return sign * MAX_INTEGER;
    }
    return value === value ? value : 0;
  }

  function toInteger(value) {
    var result = toFinite(value),
        remainder = result % 1;
    return result === result ? remainder ? result - remainder : result : 0;
  }

  function identity(value) {
    return value;
  }

  var asyncTag = '[object AsyncFunction]',
      funcTag$1 = '[object Function]',
      genTag = '[object GeneratorFunction]',
      proxyTag = '[object Proxy]';
  function isFunction(value) {
    if (!isObject(value)) {
      return false;
    }
    var tag = baseGetTag(value);
    return tag == funcTag$1 || tag == genTag || tag == asyncTag || tag == proxyTag;
  }

  var coreJsData = root['__core-js_shared__'];

  var maskSrcKey = function () {
    var uid = /[^.]+$/.exec(coreJsData && coreJsData.keys && coreJsData.keys.IE_PROTO || '');
    return uid ? 'Symbol(src)_1.' + uid : '';
  }();
  function isMasked(func) {
    return !!maskSrcKey && maskSrcKey in func;
  }

  var funcProto$2 = Function.prototype;
  var funcToString$2 = funcProto$2.toString;
  function toSource(func) {
    if (func != null) {
      try {
        return funcToString$2.call(func);
      } catch (e) {}
      try {
        return func + '';
      } catch (e) {}
    }
    return '';
  }

  var reRegExpChar = /[\\^$.*+?()[\]{}|]/g;
  var reIsHostCtor = /^\[object .+?Constructor\]$/;
  var funcProto$1 = Function.prototype,
      objectProto$8 = Object.prototype;
  var funcToString$1 = funcProto$1.toString;
  var hasOwnProperty$7 = objectProto$8.hasOwnProperty;
  var reIsNative = RegExp('^' + funcToString$1.call(hasOwnProperty$7).replace(reRegExpChar, '\\$&').replace(/hasOwnProperty|(function).*?(?=\\\()| for .+?(?=\\\])/g, '$1.*?') + '$');
  function baseIsNative(value) {
    if (!isObject(value) || isMasked(value)) {
      return false;
    }
    var pattern = isFunction(value) ? reIsNative : reIsHostCtor;
    return pattern.test(toSource(value));
  }

  function getValue(object, key) {
    return object == null ? undefined : object[key];
  }

  function getNative(object, key) {
    var value = getValue(object, key);
    return baseIsNative(value) ? value : undefined;
  }

  function apply(func, thisArg, args) {
    switch (args.length) {
      case 0:
        return func.call(thisArg);
      case 1:
        return func.call(thisArg, args[0]);
      case 2:
        return func.call(thisArg, args[0], args[1]);
      case 3:
        return func.call(thisArg, args[0], args[1], args[2]);
    }
    return func.apply(thisArg, args);
  }

  var HOT_COUNT = 800,
      HOT_SPAN = 16;
  var nativeNow = Date.now;
  function shortOut(func) {
    var count = 0,
        lastCalled = 0;
    return function () {
      var stamp = nativeNow(),
          remaining = HOT_SPAN - (stamp - lastCalled);
      lastCalled = stamp;
      if (remaining > 0) {
        if (++count >= HOT_COUNT) {
          return arguments[0];
        }
      } else {
        count = 0;
      }
      return func.apply(undefined, arguments);
    };
  }

  function constant(value) {
    return function () {
      return value;
    };
  }

  var defineProperty = function () {
    try {
      var func = getNative(Object, 'defineProperty');
      func({}, '', {});
      return func;
    } catch (e) {}
  }();

  var baseSetToString = !defineProperty ? identity : function (func, string) {
    return defineProperty(func, 'toString', {
      'configurable': true,
      'enumerable': false,
      'value': constant(string),
      'writable': true
    });
  };

  var setToString = shortOut(baseSetToString);

  function arrayEach(array, iteratee) {
    var index = -1,
        length = array == null ? 0 : array.length;
    while (++index < length) {
      if (iteratee(array[index], index, array) === false) {
        break;
      }
    }
    return array;
  }

  var MAX_SAFE_INTEGER$1 = 9007199254740991;
  var reIsUint = /^(?:0|[1-9]\d*)$/;
  function isIndex(value, length) {
    var type = typeof value;
    length = length == null ? MAX_SAFE_INTEGER$1 : length;
    return !!length && (type == 'number' || type != 'symbol' && reIsUint.test(value)) && value > -1 && value % 1 == 0 && value < length;
  }

  function baseAssignValue(object, key, value) {
    if (key == '__proto__' && defineProperty) {
      defineProperty(object, key, {
        'configurable': true,
        'enumerable': true,
        'value': value,
        'writable': true
      });
    } else {
      object[key] = value;
    }
  }

  function eq(value, other) {
    return value === other || value !== value && other !== other;
  }

  var objectProto$7 = Object.prototype;
  var hasOwnProperty$6 = objectProto$7.hasOwnProperty;
  function assignValue(object, key, value) {
    var objValue = object[key];
    if (!(hasOwnProperty$6.call(object, key) && eq(objValue, value)) || value === undefined && !(key in object)) {
      baseAssignValue(object, key, value);
    }
  }

  function copyObject(source, props, object, customizer) {
    var isNew = !object;
    object || (object = {});
    var index = -1,
        length = props.length;
    while (++index < length) {
      var key = props[index];
      var newValue = customizer ? customizer(object[key], source[key], key, object, source) : undefined;
      if (newValue === undefined) {
        newValue = source[key];
      }
      if (isNew) {
        baseAssignValue(object, key, newValue);
      } else {
        assignValue(object, key, newValue);
      }
    }
    return object;
  }

  var nativeMax = Math.max;
  function overRest(func, start, transform) {
    start = nativeMax(start === undefined ? func.length - 1 : start, 0);
    return function () {
      var args = arguments,
          index = -1,
          length = nativeMax(args.length - start, 0),
          array = Array(length);
      while (++index < length) {
        array[index] = args[start + index];
      }
      index = -1;
      var otherArgs = Array(start + 1);
      while (++index < start) {
        otherArgs[index] = args[index];
      }
      otherArgs[start] = transform(array);
      return apply(func, this, otherArgs);
    };
  }

  function baseRest(func, start) {
    return setToString(overRest(func, start, identity), func + '');
  }

  var MAX_SAFE_INTEGER = 9007199254740991;
  function isLength(value) {
    return typeof value == 'number' && value > -1 && value % 1 == 0 && value <= MAX_SAFE_INTEGER;
  }

  function isArrayLike(value) {
    return value != null && isLength(value.length) && !isFunction(value);
  }

  function isIterateeCall(value, index, object) {
    if (!isObject(object)) {
      return false;
    }
    var type = typeof index;
    if (type == 'number' ? isArrayLike(object) && isIndex(index, object.length) : type == 'string' && index in object) {
      return eq(object[index], value);
    }
    return false;
  }

  function createAssigner(assigner) {
    return baseRest(function (object, sources) {
      var index = -1,
          length = sources.length,
          customizer = length > 1 ? sources[length - 1] : undefined,
          guard = length > 2 ? sources[2] : undefined;
      customizer = assigner.length > 3 && typeof customizer == 'function' ? (length--, customizer) : undefined;
      if (guard && isIterateeCall(sources[0], sources[1], guard)) {
        customizer = length < 3 ? undefined : customizer;
        length = 1;
      }
      object = Object(object);
      while (++index < length) {
        var source = sources[index];
        if (source) {
          assigner(object, source, index, customizer);
        }
      }
      return object;
    });
  }

  var objectProto$6 = Object.prototype;
  function isPrototype(value) {
    var Ctor = value && value.constructor,
        proto = typeof Ctor == 'function' && Ctor.prototype || objectProto$6;
    return value === proto;
  }

  function baseTimes(n, iteratee) {
    var index = -1,
        result = Array(n);
    while (++index < n) {
      result[index] = iteratee(index);
    }
    return result;
  }

  var argsTag$1 = '[object Arguments]';
  function baseIsArguments(value) {
    return isObjectLike(value) && baseGetTag(value) == argsTag$1;
  }

  var objectProto$5 = Object.prototype;
  var hasOwnProperty$5 = objectProto$5.hasOwnProperty;
  var propertyIsEnumerable = objectProto$5.propertyIsEnumerable;
  var isArguments = baseIsArguments(function () {
    return arguments;
  }()) ? baseIsArguments : function (value) {
    return isObjectLike(value) && hasOwnProperty$5.call(value, 'callee') && !propertyIsEnumerable.call(value, 'callee');
  };

  function stubFalse() {
    return false;
  }

  var freeExports$1 = typeof exports == 'object' && exports && !exports.nodeType && exports;
  var freeModule$1 = freeExports$1 && typeof module == 'object' && module && !module.nodeType && module;
  var moduleExports$1 = freeModule$1 && freeModule$1.exports === freeExports$1;
  var Buffer = moduleExports$1 ? root.Buffer : undefined;
  var nativeIsBuffer = Buffer ? Buffer.isBuffer : undefined;
  var isBuffer = nativeIsBuffer || stubFalse;

  var argsTag = '[object Arguments]',
      arrayTag = '[object Array]',
      boolTag$1 = '[object Boolean]',
      dateTag = '[object Date]',
      errorTag = '[object Error]',
      funcTag = '[object Function]',
      mapTag = '[object Map]',
      numberTag$1 = '[object Number]',
      objectTag$1 = '[object Object]',
      regexpTag = '[object RegExp]',
      setTag = '[object Set]',
      stringTag$1 = '[object String]',
      weakMapTag = '[object WeakMap]';
  var arrayBufferTag = '[object ArrayBuffer]',
      dataViewTag = '[object DataView]',
      float32Tag = '[object Float32Array]',
      float64Tag = '[object Float64Array]',
      int8Tag = '[object Int8Array]',
      int16Tag = '[object Int16Array]',
      int32Tag = '[object Int32Array]',
      uint8Tag = '[object Uint8Array]',
      uint8ClampedTag = '[object Uint8ClampedArray]',
      uint16Tag = '[object Uint16Array]',
      uint32Tag = '[object Uint32Array]';
  var typedArrayTags = {};
  typedArrayTags[float32Tag] = typedArrayTags[float64Tag] = typedArrayTags[int8Tag] = typedArrayTags[int16Tag] = typedArrayTags[int32Tag] = typedArrayTags[uint8Tag] = typedArrayTags[uint8ClampedTag] = typedArrayTags[uint16Tag] = typedArrayTags[uint32Tag] = true;
  typedArrayTags[argsTag] = typedArrayTags[arrayTag] = typedArrayTags[arrayBufferTag] = typedArrayTags[boolTag$1] = typedArrayTags[dataViewTag] = typedArrayTags[dateTag] = typedArrayTags[errorTag] = typedArrayTags[funcTag] = typedArrayTags[mapTag] = typedArrayTags[numberTag$1] = typedArrayTags[objectTag$1] = typedArrayTags[regexpTag] = typedArrayTags[setTag] = typedArrayTags[stringTag$1] = typedArrayTags[weakMapTag] = false;
  function baseIsTypedArray(value) {
    return isObjectLike(value) && isLength(value.length) && !!typedArrayTags[baseGetTag(value)];
  }

  function baseUnary(func) {
    return function (value) {
      return func(value);
    };
  }

  var freeExports = typeof exports == 'object' && exports && !exports.nodeType && exports;
  var freeModule = freeExports && typeof module == 'object' && module && !module.nodeType && module;
  var moduleExports = freeModule && freeModule.exports === freeExports;
  var freeProcess = moduleExports && freeGlobal.process;
  var nodeUtil = function () {
    try {
      var types = freeModule && freeModule.require && freeModule.require('util').types;
      if (types) {
        return types;
      }
      return freeProcess && freeProcess.binding && freeProcess.binding('util');
    } catch (e) {}
  }();

  var nodeIsTypedArray = nodeUtil && nodeUtil.isTypedArray;
  var isTypedArray = nodeIsTypedArray ? baseUnary(nodeIsTypedArray) : baseIsTypedArray;

  var objectProto$4 = Object.prototype;
  var hasOwnProperty$4 = objectProto$4.hasOwnProperty;
  function arrayLikeKeys(value, inherited) {
    var isArr = isArray(value),
        isArg = !isArr && isArguments(value),
        isBuff = !isArr && !isArg && isBuffer(value),
        isType = !isArr && !isArg && !isBuff && isTypedArray(value),
        skipIndexes = isArr || isArg || isBuff || isType,
        result = skipIndexes ? baseTimes(value.length, String) : [],
        length = result.length;
    for (var key in value) {
      if ((inherited || hasOwnProperty$4.call(value, key)) && !(skipIndexes && (
      key == 'length' ||
      isBuff && (key == 'offset' || key == 'parent') ||
      isType && (key == 'buffer' || key == 'byteLength' || key == 'byteOffset') ||
      isIndex(key, length)))) {
        result.push(key);
      }
    }
    return result;
  }

  function overArg(func, transform) {
    return function (arg) {
      return func(transform(arg));
    };
  }

  var nativeKeys = overArg(Object.keys, Object);

  var objectProto$3 = Object.prototype;
  var hasOwnProperty$3 = objectProto$3.hasOwnProperty;
  function baseKeys(object) {
    if (!isPrototype(object)) {
      return nativeKeys(object);
    }
    var result = [];
    for (var key in Object(object)) {
      if (hasOwnProperty$3.call(object, key) && key != 'constructor') {
        result.push(key);
      }
    }
    return result;
  }

  function keys$1(object) {
    return isArrayLike(object) ? arrayLikeKeys(object) : baseKeys(object);
  }

  function nativeKeysIn(object) {
    var result = [];
    if (object != null) {
      for (var key in Object(object)) {
        result.push(key);
      }
    }
    return result;
  }

  var objectProto$2 = Object.prototype;
  var hasOwnProperty$2 = objectProto$2.hasOwnProperty;
  function baseKeysIn(object) {
    if (!isObject(object)) {
      return nativeKeysIn(object);
    }
    var isProto = isPrototype(object),
        result = [];
    for (var key in object) {
      if (!(key == 'constructor' && (isProto || !hasOwnProperty$2.call(object, key)))) {
        result.push(key);
      }
    }
    return result;
  }

  function keysIn(object) {
    return isArrayLike(object) ? arrayLikeKeys(object, true) : baseKeysIn(object);
  }

  var assignIn = createAssigner(function (object, source) {
    copyObject(source, keysIn(source), object);
  });

  var getPrototype = overArg(Object.getPrototypeOf, Object);

  var objectTag = '[object Object]';
  var funcProto = Function.prototype,
      objectProto$1 = Object.prototype;
  var funcToString = funcProto.toString;
  var hasOwnProperty$1 = objectProto$1.hasOwnProperty;
  var objectCtorString = funcToString.call(Object);
  function isPlainObject(value) {
    if (!isObjectLike(value) || baseGetTag(value) != objectTag) {
      return false;
    }
    var proto = getPrototype(value);
    if (proto === null) {
      return true;
    }
    var Ctor = hasOwnProperty$1.call(proto, 'constructor') && proto.constructor;
    return typeof Ctor == 'function' && Ctor instanceof Ctor && funcToString.call(Ctor) == objectCtorString;
  }

  function createBaseFor(fromRight) {
    return function (object, iteratee, keysFunc) {
      var index = -1,
          iterable = Object(object),
          props = keysFunc(object),
          length = props.length;
      while (length--) {
        var key = props[fromRight ? length : ++index];
        if (iteratee(iterable[key], key, iterable) === false) {
          break;
        }
      }
      return object;
    };
  }

  var baseFor = createBaseFor();

  function baseForOwn(object, iteratee) {
    return object && baseFor(object, iteratee, keys$1);
  }

  function createBaseEach(eachFunc, fromRight) {
    return function (collection, iteratee) {
      if (collection == null) {
        return collection;
      }
      if (!isArrayLike(collection)) {
        return eachFunc(collection, iteratee);
      }
      var length = collection.length,
          index = fromRight ? length : -1,
          iterable = Object(collection);
      while (fromRight ? index-- : ++index < length) {
        if (iteratee(iterable[index], index, iterable) === false) {
          break;
        }
      }
      return collection;
    };
  }

  var baseEach = createBaseEach(baseForOwn);

  var objectProto = Object.prototype;
  var hasOwnProperty = objectProto.hasOwnProperty;
  var defaults = baseRest(function (object, sources) {
    object = Object(object);
    var index = -1;
    var length = sources.length;
    var guard = length > 2 ? sources[2] : undefined;
    if (guard && isIterateeCall(sources[0], sources[1], guard)) {
      length = 1;
    }
    while (++index < length) {
      var source = sources[index];
      var props = keysIn(source);
      var propsIndex = -1;
      var propsLength = props.length;
      while (++propsIndex < propsLength) {
        var key = props[propsIndex];
        var value = object[key];
        if (value === undefined || eq(value, objectProto[key]) && !hasOwnProperty.call(object, key)) {
          object[key] = source[key];
        }
      }
    }
    return object;
  });

  function castFunction(value) {
    return typeof value == 'function' ? value : identity;
  }

  function forEach(collection, iteratee) {
    var func = isArray(collection) ? arrayEach : baseEach;
    return func(collection, castFunction(iteratee));
  }

  var stringTag = '[object String]';
  function isString(value) {
    return typeof value == 'string' || !isArray(value) && isObjectLike(value) && baseGetTag(value) == stringTag;
  }

  var boolTag = '[object Boolean]';
  function isBoolean(value) {
    return value === true || value === false || isObjectLike(value) && baseGetTag(value) == boolTag;
  }

  function isElement(value) {
    return isObjectLike(value) && value.nodeType === 1 && !isPlainObject(value);
  }

  function isInteger(value) {
    return typeof value == 'number' && value == toInteger(value);
  }

  var numberTag = '[object Number]';
  function isNumber(value) {
    return typeof value == 'number' || isObjectLike(value) && baseGetTag(value) == numberTag;
  }

  function checkObjType(type) {
    return function (e) {
      return Object.prototype.toString.call(e) === "[object ".concat(type, "]");
    };
  }
  function isBlob(b) {
    return checkObjType('Blob')(b);
  }
  function isFile(f) {
    return checkObjType('File')(f);
  }
  function isFileList(fl) {
    return checkObjType('FileList')(fl);
  }
  function isURL(u) {
    var urlRegex = /(http|ftp|https):\/\/[\w-]+(\.[\w-]+)+([\w.,@?^=%&amp;:\/~+#-]*[\w@?^=%&amp;\/~+#-])?/;
    return urlRegex.test(u);
  }
  function isJSONString(s) {
    try {
      JSON.parse(s);
    } catch (e) {
      return false;
    }
    return true;
  }
  function map(arr, fn) {
    return Array.prototype.slice.call(arr).map(fn);
  }
  function every(arr, fn) {
    return Array.prototype.slice.call(arr).every(fn);
  }
  function difference(a0, a1) {
    var arrays = [a0, a1];
    return arrays.reduce(function (a, b) {
      return a.filter(function (c) {
        return b.indexOf(c) === -1;
      });
    });
  }
  function keys(e) {
    return Object.keys(e || {});
  }
  function emptyFunc() {
  }
  function getRandomString() {
    return Math.random().toString(36).slice(2);
  }
  function getElement(e) {
    if (isElement(e)) {
      return e;
    } else if (isString(e)) {
      return document.querySelector(e);
    } else {
      return null;
    }
  }
  function addEvent(target, type, callback) {
    if (target.addEventListener) {
      target.addEventListener(type, callback, false);
    }
  }
  function removeEvent(target, type, callback) {
    if (target.removeEventListener) {
      target.removeEventListener(type, callback, false);
    }
  }
  function buildQueryString(params) {
    var encode = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : true;
    var esc = encode ? encodeURIComponent : function (e) {
      return e;
    };
    var ret = map(keys(params), function (k) {
      var v = params[k];
      return esc(k) + '=' + esc(isObject(v) ? JSON.stringify(v) : v);
    });
    return ret.join('&');
  }
  function ab2str(buf) {
    var bytes = Array.prototype.slice.call(new Uint8Array(buf));
    return bytes.reduce(function (acc, b) {
      return acc + String.fromCharCode.apply(null, [b]);
    }, '');
  }
  function isOneOf(elements) {
    if (!isArray(elements)) {
      throw new Error('elements should be an Array');
    }
    return function (e) {
      return elements.indexOf(e) > -1;
    };
  }
  function passesOneOf(validators) {
    if (!isArray(validators)) {
      throw new Error('validators should be an Array');
    }
    return function (e) {
      return validators.some(function (v) {
        return v(e);
      });
    };
  }
  var localStorage = function () {
    var polyfill = {
      _data: {},
      setItem: function setItem(id, val) {
        return this._data[id] = String(val);
      },
      getItem: function getItem(id) {
        return this._data.hasOwnProperty(id) ? this._data[id] : null;
      },
      removeItem: function removeItem(id) {
        return delete this._data[id];
      },
      clear: function clear() {
        return this._data = {};
      }
    };
    try {
      if ('localStorage' in window && window['localStorage'] !== null) {
        window.localStorage.setItem('store', '');
        window.localStorage.removeItem('store');
        return window.localStorage;
      } else {
        return polyfill;
      }
    } catch (e) {
      return polyfill;
    }
  }();

  function ownKeys(object, enumerableOnly) {
    var keys = Object.keys(object);

    if (Object.getOwnPropertySymbols) {
      var symbols = Object.getOwnPropertySymbols(object);

      if (enumerableOnly) {
        symbols = symbols.filter(function (sym) {
          return Object.getOwnPropertyDescriptor(object, sym).enumerable;
        });
      }

      keys.push.apply(keys, symbols);
    }

    return keys;
  }

  function _objectSpread2(target) {
    for (var i = 1; i < arguments.length; i++) {
      var source = arguments[i] != null ? arguments[i] : {};

      if (i % 2) {
        ownKeys(Object(source), true).forEach(function (key) {
          _defineProperty(target, key, source[key]);
        });
      } else if (Object.getOwnPropertyDescriptors) {
        Object.defineProperties(target, Object.getOwnPropertyDescriptors(source));
      } else {
        ownKeys(Object(source)).forEach(function (key) {
          Object.defineProperty(target, key, Object.getOwnPropertyDescriptor(source, key));
        });
      }
    }

    return target;
  }

  function _typeof(obj) {
    "@babel/helpers - typeof";

    if (typeof Symbol === "function" && typeof Symbol.iterator === "symbol") {
      _typeof = function (obj) {
        return typeof obj;
      };
    } else {
      _typeof = function (obj) {
        return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj;
      };
    }

    return _typeof(obj);
  }

  function _classCallCheck(instance, Constructor) {
    if (!(instance instanceof Constructor)) {
      throw new TypeError("Cannot call a class as a function");
    }
  }

  function _defineProperties(target, props) {
    for (var i = 0; i < props.length; i++) {
      var descriptor = props[i];
      descriptor.enumerable = descriptor.enumerable || false;
      descriptor.configurable = true;
      if ("value" in descriptor) descriptor.writable = true;
      Object.defineProperty(target, descriptor.key, descriptor);
    }
  }

  function _createClass(Constructor, protoProps, staticProps) {
    if (protoProps) _defineProperties(Constructor.prototype, protoProps);
    if (staticProps) _defineProperties(Constructor, staticProps);
    return Constructor;
  }

  function _defineProperty(obj, key, value) {
    if (key in obj) {
      Object.defineProperty(obj, key, {
        value: value,
        enumerable: true,
        configurable: true,
        writable: true
      });
    } else {
      obj[key] = value;
    }

    return obj;
  }

  function _inherits(subClass, superClass) {
    if (typeof superClass !== "function" && superClass !== null) {
      throw new TypeError("Super expression must either be null or a function");
    }

    subClass.prototype = Object.create(superClass && superClass.prototype, {
      constructor: {
        value: subClass,
        writable: true,
        configurable: true
      }
    });
    if (superClass) _setPrototypeOf(subClass, superClass);
  }

  function _getPrototypeOf(o) {
    _getPrototypeOf = Object.setPrototypeOf ? Object.getPrototypeOf : function _getPrototypeOf(o) {
      return o.__proto__ || Object.getPrototypeOf(o);
    };
    return _getPrototypeOf(o);
  }

  function _setPrototypeOf(o, p) {
    _setPrototypeOf = Object.setPrototypeOf || function _setPrototypeOf(o, p) {
      o.__proto__ = p;
      return o;
    };

    return _setPrototypeOf(o, p);
  }

  function _isNativeReflectConstruct() {
    if (typeof Reflect === "undefined" || !Reflect.construct) return false;
    if (Reflect.construct.sham) return false;
    if (typeof Proxy === "function") return true;

    try {
      Boolean.prototype.valueOf.call(Reflect.construct(Boolean, [], function () {}));
      return true;
    } catch (e) {
      return false;
    }
  }

  function _assertThisInitialized(self) {
    if (self === void 0) {
      throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
    }

    return self;
  }

  function _possibleConstructorReturn(self, call) {
    if (call && (typeof call === "object" || typeof call === "function")) {
      return call;
    }

    return _assertThisInitialized(self);
  }

  function _createSuper(Derived) {
    var hasNativeReflectConstruct = _isNativeReflectConstruct();

    return function _createSuperInternal() {
      var Super = _getPrototypeOf(Derived),
          result;

      if (hasNativeReflectConstruct) {
        var NewTarget = _getPrototypeOf(this).constructor;

        result = Reflect.construct(Super, arguments, NewTarget);
      } else {
        result = Super.apply(this, arguments);
      }

      return _possibleConstructorReturn(this, result);
    };
  }

  function _slicedToArray(arr, i) {
    return _arrayWithHoles(arr) || _iterableToArrayLimit(arr, i) || _unsupportedIterableToArray(arr, i) || _nonIterableRest();
  }

  function _toConsumableArray(arr) {
    return _arrayWithoutHoles(arr) || _iterableToArray(arr) || _unsupportedIterableToArray(arr) || _nonIterableSpread();
  }

  function _arrayWithoutHoles(arr) {
    if (Array.isArray(arr)) return _arrayLikeToArray(arr);
  }

  function _arrayWithHoles(arr) {
    if (Array.isArray(arr)) return arr;
  }

  function _iterableToArray(iter) {
    if (typeof Symbol !== "undefined" && iter[Symbol.iterator] != null || iter["@@iterator"] != null) return Array.from(iter);
  }

  function _iterableToArrayLimit(arr, i) {
    var _i = arr == null ? null : typeof Symbol !== "undefined" && arr[Symbol.iterator] || arr["@@iterator"];

    if (_i == null) return;
    var _arr = [];
    var _n = true;
    var _d = false;

    var _s, _e;

    try {
      for (_i = _i.call(arr); !(_n = (_s = _i.next()).done); _n = true) {
        _arr.push(_s.value);

        if (i && _arr.length === i) break;
      }
    } catch (err) {
      _d = true;
      _e = err;
    } finally {
      try {
        if (!_n && _i["return"] != null) _i["return"]();
      } finally {
        if (_d) throw _e;
      }
    }

    return _arr;
  }

  function _unsupportedIterableToArray(o, minLen) {
    if (!o) return;
    if (typeof o === "string") return _arrayLikeToArray(o, minLen);
    var n = Object.prototype.toString.call(o).slice(8, -1);
    if (n === "Object" && o.constructor) n = o.constructor.name;
    if (n === "Map" || n === "Set") return Array.from(o);
    if (n === "Arguments" || /^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)) return _arrayLikeToArray(o, minLen);
  }

  function _arrayLikeToArray(arr, len) {
    if (len == null || len > arr.length) len = arr.length;

    for (var i = 0, arr2 = new Array(len); i < len; i++) arr2[i] = arr[i];

    return arr2;
  }

  function _nonIterableSpread() {
    throw new TypeError("Invalid attempt to spread non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.");
  }

  function _nonIterableRest() {
    throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.");
  }

  var ua_parser = function () {
    if (!Array.isArray) {
      Array.isArray = function (arg) {
        return Object.prototype.toString.call(arg) === '[object Array]';
      };
    }
    function checkUserAgent(ua) {
      var browser = {};
      var match = /(dolfin)[ \/]([\w.]+)/.exec(ua) || /(edge)[ \/]([\w.]+)/.exec(ua) || /(chrome)[ \/]([\w.]+)/.exec(ua) || /(tizen)[ \/]([\w.]+)/.exec(ua) || /(opera)(?:.*version)?[ \/]([\w.]+)/.exec(ua) || /(webkit)(?:.*version)?[ \/]([\w.]+)/.exec(ua) || /(msie) ([\w.]+)/.exec(ua) || ua.indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+))?/.exec(ua) || ["", "unknown"];
      if (match[1] === "webkit") {
        match = /(iphone|ipad|ipod)[\S\s]*os ([\w._\-]+) like/.exec(ua) || /(android)[ \/]([\w._\-]+);/.exec(ua) || [match[0], "safari", match[2]];
      } else if (match[1] === "mozilla") {
        if (/trident/.test(ua)) {
          match[1] = "msie";
        } else {
          match[1] = "firefox";
        }
      } else if (/polaris|natebrowser|([010|011|016|017|018|019]{3}\d{3,4}\d{4}$)/.test(ua)) {
        match[1] = "polaris";
      }
      browser[match[1]] = true;
      browser.name = match[1];
      browser.version = setVersion(match[2]);
      return browser;
    }
    function setVersion(versionString) {
      var version = {};
      var versions = versionString ? versionString.split(/\.|-|_/) : ["0", "0", "0"];
      version.info = versions.join(".");
      version.major = versions[0] || "0";
      version.minor = versions[1] || "0";
      version.patch = versions[2] || "0";
      return version;
    }
    function checkPlatform(ua) {
      if (isTablet(ua)) {
        return "tablet";
      } else if (isPc(ua)) {
        return "pc";
      } else if (isMobile(ua)) {
        return "mobile";
      } else {
        return "";
      }
    }
    function isPc(ua) {
      if (ua.match(/linux|windows (nt|98)|macintosh|cros/) && !ua.match(/android|mobile|polaris|lgtelecom|uzard|natebrowser|ktf;|skt;/)) {
        return true;
      }
      return false;
    }
    function isTablet(ua) {
      if (ua.match(/ipad/) || ua.match(/android/) && !ua.match(/mobi|mini|fennec/) || ua.match(/macintosh/) && window.navigator.maxTouchPoints > 1) {
        return true;
      }
      return false;
    }
    function isMobile(ua) {
      if (!!ua.match(/ip(hone|od)|android.+mobile|windows (ce|phone)|blackberry|bb10|symbian|webos|firefox.+fennec|opera m(ob|in)i|tizen.+mobile|polaris|iemobile|lgtelecom|nokia|sonyericsson|dolfin|uzard|natebrowser|ktf;|skt;/)) {
        return true;
      } else {
        return false;
      }
    }
    function checkOs(ua) {
      var os = {},
          match = /(iphone|ipad|ipod)[\S\s]*os ([\w._\-]+) like/.exec(ua) || (/polaris|natebrowser|([010|011|016|017|018|019]{3}\d{3,4}\d{4}$)/.test(ua) ? ["", "polaris", "0.0.0"] : false) || /(windows)(?: nt | phone(?: os){0,1} | )([\w._\-]+)/.exec(ua) || /(android)[ \/]([\w._\-]+);/.exec(ua) || (/android/.test(ua) ? ["", "android", "0.0.0"] : false) || (/(windows)/.test(ua) ? ["", "windows", "0.0.0"] : false) || /(mac) os x ([\w._\-]+)/.exec(ua) || /(tizen)[ \/]([\w._\-]+);/.exec(ua) || (/(linux)/.test(ua) ? ["", "linux", "0.0.0"] : false) || (/webos/.test(ua) ? ["", "webos", "0.0.0"] : false) || /(cros)(?:\s[\w]+\s)([\d._\-]+)/.exec(ua) || /(bada)[ \/]([\w._\-]+)/.exec(ua) || (/bada/.test(ua) ? ["", "bada", "0.0.0"] : false) || (/(rim|blackberry|bb10)/.test(ua) ? ["", "blackberry", "0.0.0"] : false) || ["", "unknown", "0.0.0"];
      if (match[1] === "iphone" || match[1] === "ipad" || match[1] === "ipod") {
        match[1] = "ios";
      } else if (match[1] === "windows" && match[2] === "98") {
        match[2] = "0.98.0";
      }
      if (match[1] === "mac" && typeof window !== 'undefined' && window.navigator.maxTouchPoints > 1) {
        match[1] = "ios";
      }
      if (match[1] === 'cros') {
        match[1] = "chrome";
      }
      os[match[1]] = true;
      os.name = match[1];
      os.version = setVersion(match[2]);
      return os;
    }
    var baseAppList = ['crios', 'fxios', 'daumapps'];
    function checkApp(ua, customAppList) {
      var app = {},
          match = null,
          checkAppList = baseAppList;
      if (Array.isArray(customAppList)) {
        checkAppList = baseAppList.concat(customAppList);
      } else if (typeof customAppList === 'string') {
        checkAppList = baseAppList.concat([customAppList]);
      }
      for (var i = 0, len = checkAppList.length; i < len; i += 1) {
        var appname = checkAppList[i];
        var regex = new RegExp('(' + appname + ')[ \\/]([\\w._\\-]+)');
        match = regex.exec(ua);
        if (match) {
          break;
        }
      }
      if (!match) {
        match = ["", ""];
      }
      if (match[1]) {
        app.isApp = true;
        app.name = match[1];
        app.version = setVersion(match[2]);
      } else {
        app.isApp = false;
      }
      return app;
    }
    function getLowerUserAgent(ua) {
      var lowerUa = '';
      if (!ua) {
        if (typeof window !== 'undefined' && window.navigator && typeof window.navigator.userAgent === 'string') {
          lowerUa = window.navigator.userAgent.toLowerCase();
        } else {
          lowerUa = '';
        }
      } else {
        lowerUa = ua.toLowerCase();
      }
      return lowerUa;
    }
    var userAgent = function userAgent(ua, customAppList) {
      var lowerUa = getLowerUserAgent(ua);
      return {
        ua: lowerUa,
        browser: checkUserAgent(lowerUa),
        platform: checkPlatform(lowerUa),
        os: checkOs(lowerUa),
        app: checkApp(lowerUa, customAppList)
      };
    };
    return userAgent;
  }();

  var UA$1 = ua_parser();
  function getOrigin() {
    var _location = location,
        protocol = _location.protocol,
        hostname = _location.hostname,
        port = _location.port;
    return "".concat(protocol, "//").concat(hostname).concat(port ? ':' + port : '');
  }
  function getNavigator() {
    return navigator;
  }
  function getUA() {
    return UA$1;
  }

  var AUTH = "https://kauth.kakao.com";
  var API$1 = "https://kapi.kakao.com";
  var SHARER_DOMAIN = "https://sharer.kakao.com";
  var PICKER_DOMAIN = "https://friend-picker.kakao.com";
  var APPS_DOMAIN = "https://apps.kakao.com";
  var CHANNEL = "https://pf.kakao.com";
  var STORY = "https://story.kakao.com";
  var STORY_POST_SCHEME = "storylink://posting";
  var REDIRECT_URI = "JS-SDK";
  var UNIVERSAL_LINK = "https://talk-apps.kakao.com";
  var TALK_LOGIN_SCHEME = "kakaokompassauth://authorize";
  var TALK_LOGIN_REDIRECT_URI = "https://kapi.kakao.com/cors/afterlogin.html";
  var TALK_INAPP_SCHEME = "kakaotalk://inappbrowser";
  var TALK_SYNCPLUGIN_SCHEME = "kakaotalk://bizplugin?plugin_id=6011263b74fc2b49c73a7298";
  var TALK_LINK_SCHEME = "kakaolink://send";
  var TALK_ANDROID_PACKAGE = "com.kakao.talk";
  var NAVI_SCHEME = "kakaonavi-sdk://";
  var NAVI_WEB_URL = "https://kakaonavi-wguide.kakao.com/openapi";
  var DEVELOPERS = "https://developers.kakao.com";

  var origin = getOrigin();
  var UA = getUA();
  var isTalkWebview = /KAKAOTALK/i.test(UA.ua);
  var VERSION = "1.40.14".concat('');
  var navigator$1 = getNavigator();
  var KAKAO_AGENT = ["sdk/".concat(VERSION), 'os/javascript', 'sdk_type/javascript', "lang/".concat(navigator$1.userLanguage || navigator$1.language), "device/".concat(navigator$1.platform.replace(/ /g, '_')), "origin/".concat(encodeURIComponent(origin))].join(' ');
  var URL = {
    authDomain: AUTH,
    authorize: "".concat(AUTH, "/oauth/authorize"),
    loginWidget: "".concat(AUTH, "/public/widget/login/kakaoLoginWidget.html"),
    redirectUri: REDIRECT_URI,
    universalKakaoLink: "".concat(UNIVERSAL_LINK, "/scheme/"),
    talkLoginScheme: TALK_LOGIN_SCHEME,
    talkLoginRedirectUri: TALK_LOGIN_REDIRECT_URI,
    talkInappScheme: TALK_INAPP_SCHEME,
    talkSyncpluginScheme: TALK_SYNCPLUGIN_SCHEME,
    apiRemote: "".concat(API$1, "/cors/"),
    sharerDomain: SHARER_DOMAIN,
    pickerDomain: PICKER_DOMAIN,
    appsDomain: APPS_DOMAIN,
    talkLinkScheme: TALK_LINK_SCHEME,
    talkAndroidPackage: TALK_ANDROID_PACKAGE,
    channel: CHANNEL,
    channelIcon: "".concat(DEVELOPERS, "/assets/img/about/logos"),
    storyShare: "".concat(STORY, "/s/share"),
    storyChannelFollow: "".concat(STORY, "/s/follow"),
    storyIcon: "".concat(DEVELOPERS, "/sdk/js/resources/story/icon_small.png"),
    storyPostScheme: STORY_POST_SCHEME,
    naviStartScheme: "".concat(NAVI_SCHEME, "navigate"),
    naviShareScheme: "".concat(NAVI_SCHEME, "sharePoi"),
    naviWeb: NAVI_WEB_URL
  };
  var appKey$1 = null;
  function getAppKey$1() {
    return appKey$1;
  }
  function setAppKey(_appKey) {
    appKey$1 = _appKey;
  }
  function KakaoError(message) {
    Error.prototype.constructor.apply(this, arguments);
    this.name = 'KakaoError';
    this.message = message;
  }
  KakaoError.prototype = new Error();
  function logDebug(obj) {
  }
  function makeModule(subModules) {
    var module = assignIn.apply(void 0, [{
      cleanup: function cleanup() {
        forEach(subModules, function (e) {
          return e.cleanup && e.cleanup();
        });
      }
    }].concat(_toConsumableArray(subModules)));
    return module;
  }
  function emptyCleanups(cleanups) {
    forEach(cleanups, function (fn) {
      fn();
    });
    cleanups.length = 0;
  }
  function validate(target, validator, callerMsg) {
    if (validator(target) === false) {
      throw new KakaoError("Illegal argument for ".concat(callerMsg));
    }
  }
  function processRules() {
    var params = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};
    var rules = arguments.length > 1 ? arguments[1] : undefined;
    var callerMsg = arguments.length > 2 ? arguments[2] : undefined;
    if (!isObject(params)) {
      throw new Error('params should be an Object');
    }
    if (isFunction(rules.before)) {
      rules.before(params);
    }
    if (isFunction(rules.defaults)) {
      defaults(params, rules.defaults(params));
    } else {
      defaults(params, rules.defaults);
    }
    var _rules$required = rules.required,
        required = _rules$required === void 0 ? {} : _rules$required,
        _rules$optional = rules.optional,
        optional = _rules$optional === void 0 ? {} : _rules$optional;
    var missingRequiredKeys = difference(keys(required), keys(params));
    if (missingRequiredKeys.length > 0) {
      throw new KakaoError("Missing required keys: ".concat(missingRequiredKeys.join(','), " at ").concat(callerMsg));
    }
    var allowed = assignIn({}, required, optional);
    var invalidKeys = difference(keys(params), keys(allowed));
    if (invalidKeys.length > 0) {
      throw new KakaoError("Invalid parameter keys: ".concat(invalidKeys.join(','), " at ").concat(callerMsg));
    }
    forEach(params, function (value, key) {
      validate(value, allowed[key], "\"".concat(key, "\" in ").concat(callerMsg));
    });
    if (isFunction(rules.after)) {
      rules.after(params);
    }
    return params;
  }
  function generateTxId() {
    var tranId = Math.random().toString(36).slice(2) + getAppKey$1() + Date.now().toString(36);
    return tranId.slice(0, 60);
  }
  function getInstallUrl(androidAppId, iOSAppId) {
    if (UA.os.android) {
      var referrer = JSON.stringify({
        appKey: appKey$1,
        KA: KAKAO_AGENT
      });
      return "market://details?id=".concat(androidAppId, "&referrer=").concat(referrer);
    } else if (UA.os.ios) {
      return "https://itunes.apple.com/app/id".concat(iOSAppId);
    } else {
      return location.href;
    }
  }
  function guardCreateEasyXDM(createEasyXDM) {
    try {
      return createEasyXDM();
    } catch (e) {
      if (e instanceof TypeError) {
        throw new KakaoError('kakao.js should be loaded from a web server');
      } else {
        throw new KakaoError("EasyXDM - ".concat(e.message));
      }
    }
  }
  var popupWindows = {};
  function windowOpen(url, name, feature) {
    var popupWindow = popupWindows[name];
    if (popupWindow && popupWindow.close && !popupWindow.closed) {
      popupWindow.close();
    }
    popupWindows[name] = window.open(url, name, feature);
    return popupWindows[name];
  }
  function applyAttributes(settings, container$, mapper) {
    forEach(mapper, function (value, key) {
      var attr = container$.getAttribute(value);
      if (attr !== null) {
        settings[key] = attr === 'true' || attr === 'false' ? attr === 'true' : attr;
      }
    });
  }
  function createHiddenIframe(id, src, cleanups) {
    var iframe = document.createElement('iframe');
    iframe.id = iframe.name = id;
    iframe.src = src;
    iframe.setAttribute('style', 'border:none; width:0; height:0; display:none; overflow:hidden;');
    document.body.appendChild(iframe);
    cleanups.push(function () {
      document.body.removeChild(iframe);
    });
  }
  function addMessageEvent(settings, requestDomain, cleanups) {
    var callback = function callback(_ref) {
      var data = _ref.data,
          origin = _ref.origin;
      if (data && origin === requestDomain) {
        var resp = JSON.parse(data);
        if (resp.code) {
          settings.fail(resp);
        } else {
          settings.success(resp);
        }
        settings.always(resp);
      }
    };
    addEvent(window, 'message', callback);
    cleanups.push(function () {
      removeEvent(window, 'message', callback);
    });
  }
  function openPopupAndSubmitForm(params, popupParams) {
    var url = popupParams.url,
        popupName = popupParams.popupName,
        popupFeatures = popupParams.popupFeatures;
    var popup = UA.browser.msie ? {} : windowOpen('', popupName, popupFeatures);
    if (popup.focus) {
      popup.focus();
    }
    createAndSubmitForm(params, url, popupName);
    return popup;
  }
  function createAndSubmitForm(params, url) {
    var popupName = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : '';
    var form = document.createElement('form');
    form.setAttribute('accept-charset', 'utf-8');
    form.setAttribute('method', 'post');
    form.setAttribute('action', url);
    form.setAttribute('target', popupName);
    form.setAttribute('style', 'display:none');
    forEach(params, function (value, key) {
      var input = document.createElement('input');
      input.type = 'hidden';
      input.name = key;
      input.value = isString(value) ? value : JSON.stringify(value);
      form.appendChild(input);
    });
    document.body.appendChild(form);
    form.submit();
    document.body.removeChild(form);
  }

  var eventObserverMap = {};
  function subscribe(eventName, observer) {
    eventObserverMap[eventName] = eventObserverMap[eventName] || [];
    eventObserverMap[eventName].push(observer);
  }
  function unsubscribe(eventName, observer) {
    var observers = eventObserverMap[eventName];
    for (var i = 0; i < observers.length; i++) {
      if (observers[i] === observer) {
        observers.splice(i, 1);
        return;
      }
    }
  }
  function dispatch(eventName) {
    forEach(eventObserverMap[eventName], function (observer) {
      observer();
    });
  }
  var eventObserver = {
    subscribe: subscribe,
    unsubscribe: unsubscribe,
    dispatch: dispatch
  };

  var Poller = function () {
    function Poller(interval, maxCount) {
      _classCallCheck(this, Poller);
      this._interval = interval;
      this._maxCount = maxCount;
      this._count = 0;
      this._stopped = false;
      this._timeout = null;
    }
    _createClass(Poller, [{
      key: "start",
      value: function start(pollFunc, failFunc) {
        this._count = 0;
        this._stopped = false;
        this._doPolling(pollFunc, failFunc);
      }
    }, {
      key: "_doPolling",
      value: function _doPolling(pollFunc, failFunc) {
        var _this = this;
        if (this._stopped) return;
        this._timeout = setTimeout(function () {
          if (++_this._count > _this._maxCount) {
            failFunc();
          } else {
            pollFunc();
            _this._doPolling(pollFunc, failFunc);
          }
        }, this._interval);
      }
    }, {
      key: "stop",
      value: function stop() {
        this._stopped = true;
        clearTimeout(this._timeout);
      }
    }]);
    return Poller;
  }();

  var defaultCallbacks = {
    success: emptyFunc,
    fail: emptyFunc,
    always: emptyFunc
  };
  var loginDefaultSettings = assignIn({
    throughTalk: true,
    persistAccessToken: true,
    persistRefreshToken: false
  }, defaultCallbacks);
  var loginCommonSettings = {
    success: isFunction,
    fail: isFunction,
    always: isFunction,
    persistAccessToken: isBoolean,
    persistRefreshToken: isBoolean,
    approvalType: isOneOf(['project']),
    scope: isString,
    throughTalk: isBoolean,
    plusFriendPublicId: isString,
    channelPublicId: isString,
    serviceTerms: isString,
    redirectUri: isString,
    state: isString,
    deviceType: isOneOf(['watch', 'tv'])
  };
  var rules$8 = {
    createLoginButton: {
      required: {
        container: passesOneOf([isElement, isString])
      },
      optional: assignIn({
        lang: isOneOf(['en', 'kr']),
        size: isOneOf(['small', 'medium', 'large'])
      }, loginCommonSettings),
      defaults: assignIn({
        lang: 'kr',
        size: 'medium'
      }, loginDefaultSettings)
    },
    login: {
      optional: loginCommonSettings,
      defaults: loginDefaultSettings
    },
    authorize: {
      optional: {
        redirectUri: isString,
        approvalType: isOneOf(['project']),
        scope: isString,
        throughTalk: isBoolean,
        plusFriendPublicId: isString,
        channelPublicId: isString,
        serviceTerms: isString,
        isPopup: isBoolean,
        state: isString,
        autoLogin: isBoolean,
        deviceType: isOneOf(['watch', 'tv']),
        prompts: isString,
        reauthenticate: isBoolean,
        throughSyncplugin: isBoolean,
        success: isFunction,
        fail: isFunction,
        always: isFunction
      },
      defaults: {
        throughTalk: true,
        isPopup: false,
        reauthenticate: false,
        throughSyncplugin: true,
        success: emptyFunc,
        fail: emptyFunc,
        always: emptyFunc
      }
    },
    autoLogin: {
      optional: {
        success: isFunction,
        fail: isFunction,
        always: isFunction
      },
      defaults: defaultCallbacks
    },
    issueAccessToken: {
      required: {
        code: isString,
        redirectUri: isString
      },
      optional: {
        success: isFunction,
        fail: isFunction,
        always: isFunction
      },
      defaults: defaultCallbacks
    },
    selectShippingAddress: {
      optional: {
        success: isFunction,
        fail: isFunction,
        always: isFunction,
        returnUrl: isString
      },
      defaults: {
        success: emptyFunc,
        fail: emptyFunc,
        always: emptyFunc
      }
    },
    updateShippingAddress: {
      required: {
        addressId: isInteger
      },
      optional: {
        success: isFunction,
        fail: isFunction,
        always: isFunction,
        returnUrl: isString
      },
      defaults: {
        success: emptyFunc,
        fail: emptyFunc,
        always: emptyFunc
      }
    }
  };

  function openLoginPopup(url) {
    var LOGIN_POPUP_NAME = '_blank';
    return windowOpen(url, LOGIN_POPUP_NAME, getLoginPopupFeatures());
  }
  function getLoginPopupFeatures() {
    var popupWidth = 480;
    var popupHeight = 700;
    var sLeft = window.screenLeft ? window.screenLeft : window.screenX ? window.screenX : 0;
    var sTop = window.screenTop ? window.screenTop : window.screenY ? window.screenY : 0;
    var popupLeft = screen.width / 2 - popupWidth / 2 + sLeft;
    var popupTop = screen.height / 2 - popupHeight / 2 + sTop;
    return ["width=".concat(popupWidth), "height=".concat(popupHeight), "left=".concat(popupLeft), "top=".concat(popupTop), 'scrollbars=yes', 'resizable=1'].join(',');
  }
  function makeAuthUrl(params) {
    return "".concat(URL.authorize, "?").concat(buildQueryString(params));
  }
  function makeAuthParams(settings) {
    var params = {
      client_id: getAppKey$1()
    };
    if (settings.approvalType) {
      params['approval_type'] = settings.approvalType;
    }
    if (settings.scope) {
      params['scope'] = settings.scope;
    }
    if (settings.state) {
      params['state'] = settings.state;
    }
    return params;
  }
  function makeAuthExtraParams(settings) {
    var params = {};
    if (settings.plusFriendPublicId) {
      params['extra.plus_friend_public_id'] = settings.plusFriendPublicId;
    }
    if (settings.channelPublicId) {
      params['channel_public_id'] = settings.channelPublicId;
    }
    if (settings.serviceTerms) {
      params['extra.service_terms'] = settings.serviceTerms;
    }
    if (settings.autoLogin) {
      params['prompt'] = 'none';
    }
    if (settings.reauthenticate) {
      params['prompt'] = 'login';
    }
    if (settings.prompts) {
      params['prompt'] = settings.prompts;
    }
    if (settings.deviceType) {
      params['device_type'] = settings.deviceType;
    }
    return params;
  }
  function runAuthCallback(settings, resp) {
    if (resp.error) {
      settings.fail(resp);
    } else {
      settings.success(resp);
    }
    settings.always(resp);
  }

  function checkAuthorize(url, onResponse) {
    request$7({
      method: 'GET',
      url: url
    }, onResponse);
  }
  function request$7(req, onResponse) {
    var url = req.url,
        method = req.method,
        data = req.data;
    var xhr = new XMLHttpRequest();
    if (typeof xhr.withCredentials !== 'undefined') {
      xhr.open(method, url);
      xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
      xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
          onResponse(xhr);
        }
      };
      xhr.send(data);
    } else {
      var xdr = new XDomainRequest();
      xdr.open(method.toLowerCase(), url);
      xdr.onload = function () {
        onResponse({
          status: xdr.responseText ? 200 : 'error',
          response: xdr.responseText
        });
      };
      setTimeout(function () {
        xdr.send(data);
      }, 0);
    }
  }

  var poller$2 = new Poller(1000, 600);
  function authorize(settings) {
    settings = processRules(settings, rules$8.authorize, 'Auth.authorize');
    if (settings.autoLogin && !isTalkWebview) {
      handleResponse(settings, {
        error: 'auto_login',
        error_description: 'NOT_SUPPORTED_BROWSER'
      });
      return false;
    }
    var authTranId = generateTxId();
    var baseAuthParams = assignIn({}, makeAuthParams(settings), makeAuthExtraParams(settings), {
      redirect_uri: settings.redirectUri || URL.redirectUri,
      response_type: 'code',
      auth_tran_id: authTranId
    });
    var webAuthParams = assignIn({}, baseAuthParams, {
      ka: KAKAO_AGENT,
      is_popup: settings.isPopup
    });
    var isEasyLogin = isSupportEasyLogin(settings);
    var isSupportSyncplugin = isTalkChannelHome(settings);
    var webAuthUrl = makeAuthUrl(webAuthParams);
    var loginUrl = isEasyLogin ? makeEasyLoginUrl(settings, baseAuthParams, webAuthUrl) : webAuthUrl;
    var popup = null;
    if (isSupportSyncplugin) {
      executeSyncpluginScheme(baseAuthParams);
    } else if (settings.isPopup) {
      popup = openLoginPopup(loginUrl);
    } else {
      location.href = loginUrl;
    }
    if (isEasyLogin || isSupportSyncplugin || settings.isPopup) {
      poller$2.start(function () {
        var params = {
          client_id: getAppKey$1(),
          auth_tran_id: authTranId,
          ka: KAKAO_AGENT
        };
        checkAuthorize("".concat(URL.authDomain, "/apiweb/code.json?").concat(buildQueryString(params)), function (httpResp) {
          var isValidResp = onResponse(settings, httpResp);
          if (isValidResp) {
            poller$2.stop();
            popup && popup.close && popup.close();
          }
          if (!isEasyLogin && popup && popup.closed) {
            poller$2.stop();
          }
        });
      }, function () {
        handleResponse(settings, {
          error: 'timeout',
          error_description: 'LOGIN_TIMEOUT'
        });
      });
    }
    eventObserver.dispatch('LOGIN_START');
  }
  function isSupportEasyLogin(settings) {
    var isInAppBrowser = UA.os.ios || UA.os.android ? isTalkWebview : false;
    var isAccountLogin = settings.reauthenticate === true || settings.prompts && settings.prompts.indexOf('login') > -1;
    var isAutoLogin = settings.autoLogin === true || settings.prompts && settings.prompts.indexOf('none') > -1;
    return !isInAppBrowser && !isAccountLogin && settings.throughTalk === true && !isAutoLogin;
  }
  function onResponse(settings, httpResp) {
    if (httpResp.status === 200 && httpResp.response) {
      var resp = JSON.parse(httpResp.response);
      if (resp.status === 'ok' && resp.code) {
        handleResponse(settings, {
          code: resp.code
        });
        return true;
      } else if (resp.status === 'error' && resp.error_code && resp.error_code !== '300') {
        handleResponse(settings, {
          error: resp.error,
          error_description: resp.error_description
        });
        if (resp.error_code === '700') {
          location.href = "".concat(URL.authDomain, "/error/network");
        }
        return true;
      }
    }
    return false;
  }
  function handleResponse(settings, respObj) {
    if (settings.state) {
      respObj.state = settings.state;
    }
    if (settings.redirectUri) {
      location.href = "".concat(settings.redirectUri, "?").concat(buildQueryString(respObj));
    } else {
      runAuthCallback(settings, respObj);
    }
  }
  function makeEasyLoginUrl(settings, baseAuthParams, fallbackUrl) {
    var easyLoginAuthParams = assignIn({}, baseAuthParams, {
      is_popup: true
    });
    var getAndroidLoginIntent = function getAndroidLoginIntent() {
      var intent = ['intent:#Intent', 'action=com.kakao.talk.intent.action.CAPRI_LOGGED_IN_ACTIVITY', 'launchFlags=0x08880000', "S.com.kakao.sdk.talk.appKey=".concat(getAppKey$1()), "S.com.kakao.sdk.talk.redirectUri=".concat(easyLoginAuthParams.redirect_uri), "S.com.kakao.sdk.talk.kaHeader=".concat(KAKAO_AGENT), "S.com.kakao.sdk.talk.extraparams=".concat(encodeURIComponent(JSON.stringify(easyLoginAuthParams)))];
      if (settings.state) {
        intent.push("S.com.kakao.sdk.talk.state=".concat(settings.state));
      }
      return intent.concat(["S.browser_fallback_url=".concat(encodeURIComponent(fallbackUrl)), 'end;']).join(';');
    };
    var getIosLoginUniversalLink = function getIosLoginUniversalLink() {
      var iosLoginUrl = makeAuthUrl(easyLoginAuthParams);
      var iosFallbackUrl = settings.isPopup ? iosLoginUrl : fallbackUrl;
      var iosEasyLoginUrl = "".concat(iosLoginUrl, "&ka=").concat(encodeURIComponent(KAKAO_AGENT));
      var talkWebviewUrl = "".concat(URL.talkInappScheme, "?url=").concat(encodeURIComponent(iosEasyLoginUrl));
      return "".concat(URL.universalKakaoLink).concat(encodeURIComponent(talkWebviewUrl), "&web=").concat(encodeURIComponent(iosFallbackUrl));
    };
    return UA.os.android ? getAndroidLoginIntent() : getIosLoginUniversalLink();
  }
  function isTalkChannelHome(settings) {
    return settings.throughSyncplugin && isTalkWebview && /ch-home/i.test(UA.ua);
  }
  function executeSyncpluginScheme(baseAuthParams) {
    var bizpluginParams = assignIn({}, baseAuthParams, {
      ka: KAKAO_AGENT,
      is_popup: true,
      approval_window_type: 'v4_bizplugin'
    });
    var query = encodeURIComponent(buildQueryString(bizpluginParams));
    location.href = "".concat(URL.talkSyncpluginScheme, "&query=").concat(query);
  }

  var oauth = /*#__PURE__*/Object.freeze({
    __proto__: null,
    authorize: authorize
  });

  function isAndroidWebView() {
    return UA.os.android && (olderAndroidWebView() || oldAndroidWebView() || newerAndroidWebView());
  }
  function olderAndroidWebView() {
    return UA.os.version.major == 2 && /Version\/\d+.\d+|/i.test(UA.ua);
  }
  function oldAndroidWebView() {
    return UA.os.version.major == 4 && UA.os.version.minor < 4 && /Version\/\d+.\d+|/i.test(UA.ua);
  }
  function newerAndroidWebView() {
    return /Version\/\d+\.\d+/i.test(UA.ua) && (/Chrome\/\d+\.\d+\.\d+\.\d+ Mobile/i.test(UA.ua) || /; wv\)/i.test(UA.ua));
  }
  function isIOSKakaoTalkWebView() {
    return UA.os.ios && isTalkWebview;
  }
  function isAndroidKakaoTalkWebView() {
    return UA.os.android && isTalkWebview;
  }
  function isNewerAndroidKakaoTalkWebView() {
    return UA.os.android && isTalkWebview && UA.browser.chrome && UA.browser.version.major >= 71;
  }

  var commonjsGlobal = typeof globalThis !== 'undefined' ? globalThis : typeof window !== 'undefined' ? window : typeof global !== 'undefined' ? global : typeof self !== 'undefined' ? self : {};

  function createCommonjsModule(fn) {
    var module = { exports: {} };
  	return fn(module, module.exports), module.exports;
  }

  function commonjsRequire (target) {
  	throw new Error('Could not dynamically require "' + target + '". Please configure the dynamicRequireTargets option of @rollup/plugin-commonjs appropriately for this require call to behave properly.');
  }

  var es6Promise = createCommonjsModule(function (module, exports) {
    (function (global, factory) {
      module.exports = factory() ;
    })(commonjsGlobal, function () {
      function objectOrFunction(x) {
        var type = typeof x;
        return x !== null && (type === 'object' || type === 'function');
      }
      function isFunction(x) {
        return typeof x === 'function';
      }
      var _isArray = void 0;
      if (Array.isArray) {
        _isArray = Array.isArray;
      } else {
        _isArray = function (x) {
          return Object.prototype.toString.call(x) === '[object Array]';
        };
      }
      var isArray = _isArray;
      var len = 0;
      var vertxNext = void 0;
      var customSchedulerFn = void 0;
      var asap = function asap(callback, arg) {
        queue[len] = callback;
        queue[len + 1] = arg;
        len += 2;
        if (len === 2) {
          if (customSchedulerFn) {
            customSchedulerFn(flush);
          } else {
            scheduleFlush();
          }
        }
      };
      function setScheduler(scheduleFn) {
        customSchedulerFn = scheduleFn;
      }
      function setAsap(asapFn) {
        asap = asapFn;
      }
      var browserWindow = typeof window !== 'undefined' ? window : undefined;
      var browserGlobal = browserWindow || {};
      var BrowserMutationObserver = browserGlobal.MutationObserver || browserGlobal.WebKitMutationObserver;
      var isNode = typeof self === 'undefined' && typeof process !== 'undefined' && {}.toString.call(process) === '[object process]';
      var isWorker = typeof Uint8ClampedArray !== 'undefined' && typeof importScripts !== 'undefined' && typeof MessageChannel !== 'undefined';
      function useNextTick() {
        return function () {
          return process.nextTick(flush);
        };
      }
      function useVertxTimer() {
        if (typeof vertxNext !== 'undefined') {
          return function () {
            vertxNext(flush);
          };
        }
        return useSetTimeout();
      }
      function useMutationObserver() {
        var iterations = 0;
        var observer = new BrowserMutationObserver(flush);
        var node = document.createTextNode('');
        observer.observe(node, {
          characterData: true
        });
        return function () {
          node.data = iterations = ++iterations % 2;
        };
      }
      function useMessageChannel() {
        var channel = new MessageChannel();
        channel.port1.onmessage = flush;
        return function () {
          return channel.port2.postMessage(0);
        };
      }
      function useSetTimeout() {
        var globalSetTimeout = setTimeout;
        return function () {
          return globalSetTimeout(flush, 1);
        };
      }
      var queue = new Array(1000);
      function flush() {
        for (var i = 0; i < len; i += 2) {
          var callback = queue[i];
          var arg = queue[i + 1];
          callback(arg);
          queue[i] = undefined;
          queue[i + 1] = undefined;
        }
        len = 0;
      }
      function attemptVertx() {
        try {
          var vertx = Function('return this')().require('vertx');
          vertxNext = vertx.runOnLoop || vertx.runOnContext;
          return useVertxTimer();
        } catch (e) {
          return useSetTimeout();
        }
      }
      var scheduleFlush = void 0;
      if (isNode) {
        scheduleFlush = useNextTick();
      } else if (BrowserMutationObserver) {
        scheduleFlush = useMutationObserver();
      } else if (isWorker) {
        scheduleFlush = useMessageChannel();
      } else if (browserWindow === undefined && typeof commonjsRequire === 'function') {
        scheduleFlush = attemptVertx();
      } else {
        scheduleFlush = useSetTimeout();
      }
      function then(onFulfillment, onRejection) {
        var parent = this;
        var child = new this.constructor(noop);
        if (child[PROMISE_ID] === undefined) {
          makePromise(child);
        }
        var _state = parent._state;
        if (_state) {
          var callback = arguments[_state - 1];
          asap(function () {
            return invokeCallback(_state, child, callback, parent._result);
          });
        } else {
          subscribe(parent, child, onFulfillment, onRejection);
        }
        return child;
      }
      function resolve$1(object) {
        var Constructor = this;
        if (object && typeof object === 'object' && object.constructor === Constructor) {
          return object;
        }
        var promise = new Constructor(noop);
        resolve(promise, object);
        return promise;
      }
      var PROMISE_ID = Math.random().toString(36).substring(2);
      function noop() {}
      var PENDING = void 0;
      var FULFILLED = 1;
      var REJECTED = 2;
      function selfFulfillment() {
        return new TypeError("You cannot resolve a promise with itself");
      }
      function cannotReturnOwn() {
        return new TypeError('A promises callback cannot return that same promise.');
      }
      function tryThen(then$$1, value, fulfillmentHandler, rejectionHandler) {
        try {
          then$$1.call(value, fulfillmentHandler, rejectionHandler);
        } catch (e) {
          return e;
        }
      }
      function handleForeignThenable(promise, thenable, then$$1) {
        asap(function (promise) {
          var sealed = false;
          var error = tryThen(then$$1, thenable, function (value) {
            if (sealed) {
              return;
            }
            sealed = true;
            if (thenable !== value) {
              resolve(promise, value);
            } else {
              fulfill(promise, value);
            }
          }, function (reason) {
            if (sealed) {
              return;
            }
            sealed = true;
            reject(promise, reason);
          }, 'Settle: ' + (promise._label || ' unknown promise'));
          if (!sealed && error) {
            sealed = true;
            reject(promise, error);
          }
        }, promise);
      }
      function handleOwnThenable(promise, thenable) {
        if (thenable._state === FULFILLED) {
          fulfill(promise, thenable._result);
        } else if (thenable._state === REJECTED) {
          reject(promise, thenable._result);
        } else {
          subscribe(thenable, undefined, function (value) {
            return resolve(promise, value);
          }, function (reason) {
            return reject(promise, reason);
          });
        }
      }
      function handleMaybeThenable(promise, maybeThenable, then$$1) {
        if (maybeThenable.constructor === promise.constructor && then$$1 === then && maybeThenable.constructor.resolve === resolve$1) {
          handleOwnThenable(promise, maybeThenable);
        } else {
          if (then$$1 === undefined) {
            fulfill(promise, maybeThenable);
          } else if (isFunction(then$$1)) {
            handleForeignThenable(promise, maybeThenable, then$$1);
          } else {
            fulfill(promise, maybeThenable);
          }
        }
      }
      function resolve(promise, value) {
        if (promise === value) {
          reject(promise, selfFulfillment());
        } else if (objectOrFunction(value)) {
          var then$$1 = void 0;
          try {
            then$$1 = value.then;
          } catch (error) {
            reject(promise, error);
            return;
          }
          handleMaybeThenable(promise, value, then$$1);
        } else {
          fulfill(promise, value);
        }
      }
      function publishRejection(promise) {
        if (promise._onerror) {
          promise._onerror(promise._result);
        }
        publish(promise);
      }
      function fulfill(promise, value) {
        if (promise._state !== PENDING) {
          return;
        }
        promise._result = value;
        promise._state = FULFILLED;
        if (promise._subscribers.length !== 0) {
          asap(publish, promise);
        }
      }
      function reject(promise, reason) {
        if (promise._state !== PENDING) {
          return;
        }
        promise._state = REJECTED;
        promise._result = reason;
        asap(publishRejection, promise);
      }
      function subscribe(parent, child, onFulfillment, onRejection) {
        var _subscribers = parent._subscribers;
        var length = _subscribers.length;
        parent._onerror = null;
        _subscribers[length] = child;
        _subscribers[length + FULFILLED] = onFulfillment;
        _subscribers[length + REJECTED] = onRejection;
        if (length === 0 && parent._state) {
          asap(publish, parent);
        }
      }
      function publish(promise) {
        var subscribers = promise._subscribers;
        var settled = promise._state;
        if (subscribers.length === 0) {
          return;
        }
        var child = void 0,
            callback = void 0,
            detail = promise._result;
        for (var i = 0; i < subscribers.length; i += 3) {
          child = subscribers[i];
          callback = subscribers[i + settled];
          if (child) {
            invokeCallback(settled, child, callback, detail);
          } else {
            callback(detail);
          }
        }
        promise._subscribers.length = 0;
      }
      function invokeCallback(settled, promise, callback, detail) {
        var hasCallback = isFunction(callback),
            value = void 0,
            error = void 0,
            succeeded = true;
        if (hasCallback) {
          try {
            value = callback(detail);
          } catch (e) {
            succeeded = false;
            error = e;
          }
          if (promise === value) {
            reject(promise, cannotReturnOwn());
            return;
          }
        } else {
          value = detail;
        }
        if (promise._state !== PENDING) ; else if (hasCallback && succeeded) {
          resolve(promise, value);
        } else if (succeeded === false) {
          reject(promise, error);
        } else if (settled === FULFILLED) {
          fulfill(promise, value);
        } else if (settled === REJECTED) {
          reject(promise, value);
        }
      }
      function initializePromise(promise, resolver) {
        try {
          resolver(function resolvePromise(value) {
            resolve(promise, value);
          }, function rejectPromise(reason) {
            reject(promise, reason);
          });
        } catch (e) {
          reject(promise, e);
        }
      }
      var id = 0;
      function nextId() {
        return id++;
      }
      function makePromise(promise) {
        promise[PROMISE_ID] = id++;
        promise._state = undefined;
        promise._result = undefined;
        promise._subscribers = [];
      }
      function validationError() {
        return new Error('Array Methods must be provided an Array');
      }
      var Enumerator = function () {
        function Enumerator(Constructor, input) {
          this._instanceConstructor = Constructor;
          this.promise = new Constructor(noop);
          if (!this.promise[PROMISE_ID]) {
            makePromise(this.promise);
          }
          if (isArray(input)) {
            this.length = input.length;
            this._remaining = input.length;
            this._result = new Array(this.length);
            if (this.length === 0) {
              fulfill(this.promise, this._result);
            } else {
              this.length = this.length || 0;
              this._enumerate(input);
              if (this._remaining === 0) {
                fulfill(this.promise, this._result);
              }
            }
          } else {
            reject(this.promise, validationError());
          }
        }
        Enumerator.prototype._enumerate = function _enumerate(input) {
          for (var i = 0; this._state === PENDING && i < input.length; i++) {
            this._eachEntry(input[i], i);
          }
        };
        Enumerator.prototype._eachEntry = function _eachEntry(entry, i) {
          var c = this._instanceConstructor;
          var resolve$$1 = c.resolve;
          if (resolve$$1 === resolve$1) {
            var _then = void 0;
            var error = void 0;
            var didError = false;
            try {
              _then = entry.then;
            } catch (e) {
              didError = true;
              error = e;
            }
            if (_then === then && entry._state !== PENDING) {
              this._settledAt(entry._state, i, entry._result);
            } else if (typeof _then !== 'function') {
              this._remaining--;
              this._result[i] = entry;
            } else if (c === Promise$1) {
              var promise = new c(noop);
              if (didError) {
                reject(promise, error);
              } else {
                handleMaybeThenable(promise, entry, _then);
              }
              this._willSettleAt(promise, i);
            } else {
              this._willSettleAt(new c(function (resolve$$1) {
                return resolve$$1(entry);
              }), i);
            }
          } else {
            this._willSettleAt(resolve$$1(entry), i);
          }
        };
        Enumerator.prototype._settledAt = function _settledAt(state, i, value) {
          var promise = this.promise;
          if (promise._state === PENDING) {
            this._remaining--;
            if (state === REJECTED) {
              reject(promise, value);
            } else {
              this._result[i] = value;
            }
          }
          if (this._remaining === 0) {
            fulfill(promise, this._result);
          }
        };
        Enumerator.prototype._willSettleAt = function _willSettleAt(promise, i) {
          var enumerator = this;
          subscribe(promise, undefined, function (value) {
            return enumerator._settledAt(FULFILLED, i, value);
          }, function (reason) {
            return enumerator._settledAt(REJECTED, i, reason);
          });
        };
        return Enumerator;
      }();
      function all(entries) {
        return new Enumerator(this, entries).promise;
      }
      function race(entries) {
        var Constructor = this;
        if (!isArray(entries)) {
          return new Constructor(function (_, reject) {
            return reject(new TypeError('You must pass an array to race.'));
          });
        } else {
          return new Constructor(function (resolve, reject) {
            var length = entries.length;
            for (var i = 0; i < length; i++) {
              Constructor.resolve(entries[i]).then(resolve, reject);
            }
          });
        }
      }
      function reject$1(reason) {
        var Constructor = this;
        var promise = new Constructor(noop);
        reject(promise, reason);
        return promise;
      }
      function needsResolver() {
        throw new TypeError('You must pass a resolver function as the first argument to the promise constructor');
      }
      function needsNew() {
        throw new TypeError("Failed to construct 'Promise': Please use the 'new' operator, this object constructor cannot be called as a function.");
      }
      var Promise$1 = function () {
        function Promise(resolver) {
          this[PROMISE_ID] = nextId();
          this._result = this._state = undefined;
          this._subscribers = [];
          if (noop !== resolver) {
            typeof resolver !== 'function' && needsResolver();
            this instanceof Promise ? initializePromise(this, resolver) : needsNew();
          }
        }
        Promise.prototype.catch = function _catch(onRejection) {
          return this.then(null, onRejection);
        };
        Promise.prototype.finally = function _finally(callback) {
          var promise = this;
          var constructor = promise.constructor;
          if (isFunction(callback)) {
            return promise.then(function (value) {
              return constructor.resolve(callback()).then(function () {
                return value;
              });
            }, function (reason) {
              return constructor.resolve(callback()).then(function () {
                throw reason;
              });
            });
          }
          return promise.then(callback, callback);
        };
        return Promise;
      }();
      Promise$1.prototype.then = then;
      Promise$1.all = all;
      Promise$1.race = race;
      Promise$1.resolve = resolve$1;
      Promise$1.reject = reject$1;
      Promise$1._setScheduler = setScheduler;
      Promise$1._setAsap = setAsap;
      Promise$1._asap = asap;
      function polyfill() {
        var local = void 0;
        if (typeof commonjsGlobal !== 'undefined') {
          local = commonjsGlobal;
        } else if (typeof self !== 'undefined') {
          local = self;
        } else {
          try {
            local = Function('return this')();
          } catch (e) {
            throw new Error('polyfill failed because global object is unavailable in this environment');
          }
        }
        var P = local.Promise;
        if (P) {
          var promiseToString = null;
          try {
            promiseToString = Object.prototype.toString.call(P.resolve());
          } catch (e) {
          }
          if (promiseToString === '[object Promise]' && !P.cast) {
            return;
          }
        }
        local.Promise = Promise$1;
      }
      Promise$1.polyfill = polyfill;
      Promise$1.Promise = Promise$1;
      return Promise$1;
    });
  });

  var easyXDM_1 = function () {
    (function (O, d, q, L, l, I) {
      var b = this || O;
      var o = Math.floor(Math.random() * 10000);
      var r = Function.prototype;
      var R = /^((http.?:)\/\/([^:\/\s]+)(:\d+)*)/;
      var S = /[\-\w]+\/\.\.\//;
      var G = /([^:])\/\//g;
      var J = "";
      var p = {};
      var N = O.easyXDM;
      var V = "easyXDM_";
      var F;
      var z = false;
      var j;
      var i;
      function D(Y, aa) {
        var Z = _typeof(Y[aa]);
        return Z == "function" || !!(Z == "object" && Y[aa]) || Z == "unknown";
      }
      function v(Y, Z) {
        return !!(_typeof(Y[Z]) == "object" && Y[Z]);
      }
      function s(Y) {
        return Object.prototype.toString.call(Y) === "[object Array]";
      }
      function c() {
        var aa = "Shockwave Flash",
            ae = "application/x-shockwave-flash";
        if (!u(navigator.plugins) && _typeof(navigator.plugins[aa]) == "object") {
          var ac = navigator.plugins[aa].description;
          if (ac && !u(navigator.mimeTypes) && navigator.mimeTypes[ae] && navigator.mimeTypes[ae].enabledPlugin) {
            j = ac.match(/\d+/g);
          }
        }
        if (!j) {
          var Z;
          try {
            Z = new ActiveXObject("ShockwaveFlash.ShockwaveFlash");
            j = Array.prototype.slice.call(Z.GetVariable("$version").match(/(\d+),(\d+),(\d+),(\d+)/), 1);
            Z = null;
          } catch (ad) {}
        }
        if (!j) {
          return false;
        }
        var Y = parseInt(j[0], 10),
            ab = parseInt(j[1], 10);
        i = Y > 9 && ab > 0;
        return true;
      }
      var w, y;
      if (D(O, "addEventListener")) {
        w = function w(aa, Y, Z) {
          aa.addEventListener(Y, Z, false);
        };
        y = function y(aa, Y, Z) {
          aa.removeEventListener(Y, Z, false);
        };
      } else {
        if (D(O, "attachEvent")) {
          w = function w(Y, aa, Z) {
            Y.attachEvent("on" + aa, Z);
          };
          y = function y(Y, aa, Z) {
            Y.detachEvent("on" + aa, Z);
          };
        } else {
          throw new Error("Browser not supported");
        }
      }
      var X = false,
          K = [],
          M;
      if ("readyState" in d) {
        M = d.readyState;
        X = M == "complete" || ~navigator.userAgent.indexOf("AppleWebKit/") && (M == "loaded" || M == "interactive");
      } else {
        X = !!d.body;
      }
      function t() {
        if (X) {
          return;
        }
        X = true;
        for (var Y = 0; Y < K.length; Y++) {
          K[Y]();
        }
        K.length = 0;
      }
      if (!X) {
        if (D(O, "addEventListener")) {
          w(d, "DOMContentLoaded", t);
        } else {
          w(d, "readystatechange", function () {
            if (d.readyState == "complete") {
              t();
            }
          });
          if (d.documentElement.doScroll && O === top) {
            var h = function h() {
              if (X) {
                return;
              }
              try {
                d.documentElement.doScroll("left");
              } catch (Y) {
                L(h, 1);
                return;
              }
              t();
            };
            h();
          }
        }
        w(O, "load", t);
      }
      function H(Z, Y) {
        if (X) {
          Z.call(Y);
          return;
        }
        K.push(function () {
          Z.call(Y);
        });
      }
      function n() {
        var aa = parent;
        if (J !== "") {
          for (var Y = 0, Z = J.split("."); Y < Z.length; Y++) {
            aa = aa[Z[Y]];
          }
        }
        return aa.easyXDM;
      }
      function f(Y) {
        O.easyXDM = N;
        J = Y;
        if (J) {
          V = "easyXDM_" + J.replace(".", "_") + "_";
        }
        return p;
      }
      function A(Y) {
        return Y.match(R)[3];
      }
      function g(Y) {
        return Y.match(R)[4] || "";
      }
      function k(aa) {
        if (aa.indexOf("file://") >= 0) {
          return "file://";
        }
        var Y = aa.toLowerCase().match(R);
        if (!Y) {
          return "";
        }
        var ab = Y[2],
            ac = Y[3],
            Z = Y[4] || "";
        if (ab == "http:" && Z == ":80" || ab == "https:" && Z == ":443") {
          Z = "";
        }
        return ab + "//" + ac + Z;
      }
      function C(Y) {
        Y = Y.replace(G, "$1/");
        if (!Y.match(/^(http||https):\/\//)) {
          var Z = Y.substring(0, 1) === "/" ? "" : q.pathname;
          if (Z.substring(Z.length - 1) !== "/") {
            Z = Z.substring(0, Z.lastIndexOf("/") + 1);
          }
          Y = q.protocol + "//" + q.host + Z + Y;
        }
        while (S.test(Y)) {
          Y = Y.replace(S, "");
        }
        return Y;
      }
      function Q(Y, ab) {
        var ad = "",
            aa = Y.indexOf("#");
        if (aa !== -1) {
          ad = Y.substring(aa);
          Y = Y.substring(0, aa);
        }
        var ac = [];
        for (var Z in ab) {
          if (ab.hasOwnProperty(Z)) {
            ac.push(Z + "=" + I(ab[Z]));
          }
        }
        return Y + (z ? "#" : Y.indexOf("?") == -1 ? "?" : "&") + ac.join("&") + ad;
      }
      var T = function (Y) {
        Y = Y.substring(1).split("&");
        var aa = {},
            ab,
            Z = Y.length;
        while (Z--) {
          ab = Y[Z].split("=");
          aa[ab[0]] = l(ab[1]);
        }
        return aa;
      }(/xdm_e=/.test(q.search) ? q.search : q.hash);
      function u(Y) {
        return typeof Y === "undefined";
      }
      var _P = function P() {
        var Z = {};
        var aa = {
          a: [1, 2, 3]
        },
            Y = '{"a":[1,2,3]}';
        if (typeof JSON != "undefined" && typeof JSON.stringify === "function" && JSON.stringify(aa).replace(/\s/g, "") === Y) {
          return JSON;
        }
        if (Object.toJSON) {
          if (Object.toJSON(aa).replace(/\s/g, "") === Y) {
            Z.stringify = Object.toJSON;
          }
        }
        if (typeof String.prototype.evalJSON === "function") {
          aa = Y.evalJSON();
          if (aa.a && aa.a.length === 3 && aa.a[2] === 3) {
            Z.parse = function (ab) {
              return ab.evalJSON();
            };
          }
        }
        if (Z.stringify && Z.parse) {
          _P = function P() {
            return Z;
          };
          return Z;
        }
        return null;
      };
      function U(Y, Z, aa) {
        var ac;
        for (var ab in Z) {
          if (Z.hasOwnProperty(ab)) {
            if (ab in Y) {
              ac = Z[ab];
              if (_typeof(ac) === "object") {
                U(Y[ab], ac, aa);
              } else {
                if (!aa) {
                  Y[ab] = Z[ab];
                }
              }
            } else {
              Y[ab] = Z[ab];
            }
          }
        }
        return Y;
      }
      function a() {
        var Z = d.body.appendChild(d.createElement("form")),
            Y = Z.appendChild(d.createElement("input"));
        Y.name = V + "TEST" + o;
        F = Y !== Z.elements[Y.name];
        d.body.removeChild(Z);
      }
      function B(Z) {
        if (u(F)) {
          a();
        }
        var ad;
        if (F) {
          ad = d.createElement('<iframe name="' + Z.props.name + '"/>');
        } else {
          ad = d.createElement("IFRAME");
          ad.name = Z.props.name;
        }
        ad.id = ad.name = Z.props.name;
        delete Z.props.name;
        if (typeof Z.container == "string") {
          Z.container = d.getElementById(Z.container);
        }
        if (!Z.container) {
          U(ad.style, {
            position: "absolute",
            top: "-2000px",
            left: "0px"
          });
          Z.container = d.body;
        }
        var ac = Z.props.src;
        Z.props.src = "javascript:false";
        U(ad, Z.props);
        ad.border = ad.frameBorder = 0;
        ad.allowTransparency = true;
        Z.container.appendChild(ad);
        if (Z.onLoad) {
          w(ad, "load", Z.onLoad);
        }
        if (Z.usePost) {
          var ab = Z.container.appendChild(d.createElement("form")),
              Y;
          ab.target = ad.name;
          ab.action = ac;
          ab.method = "POST";
          if (_typeof(Z.usePost) === "object") {
            for (var aa in Z.usePost) {
              if (Z.usePost.hasOwnProperty(aa)) {
                if (F) {
                  Y = d.createElement('<input name="' + aa + '"/>');
                } else {
                  Y = d.createElement("INPUT");
                  Y.name = aa;
                }
                Y.value = Z.usePost[aa];
                ab.appendChild(Y);
              }
            }
          }
          ab.submit();
          ab.parentNode.removeChild(ab);
        } else {
          ad.src = ac;
        }
        Z.props.src = ac;
        return ad;
      }
      function e(Y) {
        return Y.replace(/[-[\]/{}()+.\^$|]/g, "\\$&").replace(/(\*)/g, ".$1").replace(/\?/g, ".");
      }
      function W(ac, aa) {
        if (typeof ac == "string") {
          ac = [ac];
        }
        var Z,
            Y = ac.length;
        while (Y--) {
          var ab = ac[Y].substr(0, 1) === "^" && ac[Y].substr(ac[Y].length - 1, 1) === "$";
          Z = ab ? ac[Y] : "^" + e(ac[Y]) + "$";
          Z = new RegExp(Z);
          if (Z.test(aa)) {
            return true;
          }
        }
        return false;
      }
      function m(aa) {
        var af = aa.protocol,
            Z;
        aa.isHost = aa.isHost || u(T.xdm_p);
        z = aa.hash || false;
        if (!aa.props) {
          aa.props = {};
        }
        if (!aa.isHost) {
          aa.channel = T.xdm_c.replace(/["'<>\\]/g, "");
          aa.secret = T.xdm_s;
          aa.remote = T.xdm_e.replace(/["'<>\\]/g, "");
          af = T.xdm_p;
          if (aa.acl && !W(aa.acl, aa.remote)) {
            throw new Error("Access denied for " + aa.remote);
          }
        } else {
          aa.remote = C(aa.remote);
          aa.channel = aa.channel || "default" + o++;
          aa.secret = Math.random().toString(16).substring(2);
          if (u(af)) {
            if (k(q.href) == k(aa.remote)) {
              af = "4";
            } else {
              if (D(O, "postMessage") || D(d, "postMessage")) {
                af = "1";
              } else {
                if (aa.swf && D(O, "ActiveXObject") && c()) {
                  af = "6";
                } else {
                  if (navigator.product === "Gecko" && "frameElement" in O && navigator.userAgent.indexOf("WebKit") == -1) {
                    af = "5";
                  } else {
                    if (aa.remoteHelper) {
                      af = "2";
                    } else {
                      af = "0";
                    }
                  }
                }
              }
            }
          }
        }
        aa.protocol = af;
        switch (af) {
          case "0":
            U(aa, {
              interval: 100,
              delay: 2000,
              useResize: true,
              useParent: false,
              usePolling: false
            }, true);
            if (aa.isHost) {
              if (!aa.local) {
                var ad = q.protocol + "//" + q.host,
                    Y = d.body.getElementsByTagName("img"),
                    ae;
                var ab = Y.length;
                while (ab--) {
                  ae = Y[ab];
                  if (ae.src.substring(0, ad.length) === ad) {
                    aa.local = ae.src;
                    break;
                  }
                }
                if (!aa.local) {
                  aa.local = O;
                }
              }
              var ac = {
                xdm_c: aa.channel,
                xdm_p: 0
              };
              if (aa.local === O) {
                aa.usePolling = true;
                aa.useParent = true;
                aa.local = q.protocol + "//" + q.host + q.pathname + q.search;
                ac.xdm_e = aa.local;
                ac.xdm_pa = 1;
              } else {
                ac.xdm_e = C(aa.local);
              }
              if (aa.container) {
                aa.useResize = false;
                ac.xdm_po = 1;
              }
              aa.remote = Q(aa.remote, ac);
            } else {
              U(aa, {
                useParent: !u(T.xdm_pa),
                usePolling: !u(T.xdm_po),
                useResize: aa.useParent ? false : aa.useResize
              });
            }
            Z = [new p.stack.HashTransport(aa), new p.stack.ReliableBehavior({}), new p.stack.QueueBehavior({
              encode: true,
              maxLength: 4000 - aa.remote.length
            }), new p.stack.VerifyBehavior({
              initiate: aa.isHost
            })];
            break;
          case "1":
            Z = [new p.stack.PostMessageTransport(aa)];
            break;
          case "2":
            if (aa.isHost) {
              aa.remoteHelper = C(aa.remoteHelper);
            }
            Z = [new p.stack.NameTransport(aa), new p.stack.QueueBehavior(), new p.stack.VerifyBehavior({
              initiate: aa.isHost
            })];
            break;
          case "3":
            Z = [new p.stack.NixTransport(aa)];
            break;
          case "4":
            Z = [new p.stack.SameOriginTransport(aa)];
            break;
          case "5":
            Z = [new p.stack.FrameElementTransport(aa)];
            break;
          case "6":
            if (!j) {
              c();
            }
            Z = [new p.stack.FlashTransport(aa)];
            break;
        }
        Z.push(new p.stack.QueueBehavior({
          lazy: aa.lazy,
          remove: true
        }));
        return Z;
      }
      function E(ab) {
        var ac,
            aa = {
          incoming: function incoming(ae, ad) {
            this.up.incoming(ae, ad);
          },
          outgoing: function outgoing(ad, ae) {
            this.down.outgoing(ad, ae);
          },
          callback: function callback(ad) {
            this.up.callback(ad);
          },
          init: function init() {
            this.down.init();
          },
          destroy: function destroy() {
            this.down.destroy();
          }
        };
        for (var Z = 0, Y = ab.length; Z < Y; Z++) {
          ac = ab[Z];
          U(ac, aa, true);
          if (Z !== 0) {
            ac.down = ab[Z - 1];
          }
          if (Z !== Y - 1) {
            ac.up = ab[Z + 1];
          }
        }
        return ac;
      }
      function x(Y) {
        Y.up.down = Y.down;
        Y.down.up = Y.up;
        Y.up = Y.down = null;
      }
      U(p, {
        version: "2.5.00.1",
        query: T,
        stack: {},
        apply: U,
        getJSONObject: _P,
        whenReady: H,
        noConflict: f
      });
      p.DomHelper = {
        on: w,
        un: y,
        requiresJSON: function requiresJSON(Y) {
          if (!v(O, "JSON")) {
            d.write('<script type="text/javascript" src="' + Y + '"><\/script>');
          }
        }
      };
      (function () {
        var Y = {};
        p.Fn = {
          set: function set(Z, aa) {
            Y[Z] = aa;
          },
          get: function get(aa, Z) {
            if (!Y.hasOwnProperty(aa)) {
              return;
            }
            var ab = Y[aa];
            if (Z) {
              delete Y[aa];
            }
            return ab;
          }
        };
      })();
      p.Socket = function (Z) {
        var Y = E(m(Z).concat([{
          incoming: function incoming(ac, ab) {
            Z.onMessage(ac, ab);
          },
          callback: function callback(ab) {
            if (Z.onReady) {
              Z.onReady(ab);
            }
          }
        }])),
            aa = k(Z.remote);
        this.origin = k(Z.remote);
        this.destroy = function () {
          Y.destroy();
        };
        this.postMessage = function (ab) {
          Y.outgoing(ab, aa);
        };
        Y.init();
      };
      p.Rpc = function (aa, Z) {
        if (Z.local) {
          for (var ac in Z.local) {
            if (Z.local.hasOwnProperty(ac)) {
              var ab = Z.local[ac];
              if (typeof ab === "function") {
                Z.local[ac] = {
                  method: ab
                };
              }
            }
          }
        }
        var Y = E(m(aa).concat([new p.stack.RpcBehavior(this, Z), {
          callback: function callback(ad) {
            if (aa.onReady) {
              aa.onReady(ad);
            }
          }
        }]));
        this.origin = k(aa.remote);
        this.context = aa.context || null;
        this.destroy = function () {
          Y.destroy();
        };
        Y.init();
      };
      p.stack.SameOriginTransport = function (Z) {
        var aa, ac, ab, Y;
        return aa = {
          outgoing: function outgoing(ae, af, ad) {
            ab(ae);
            if (ad) {
              ad();
            }
          },
          destroy: function destroy() {
            if (ac) {
              ac.parentNode.removeChild(ac);
              ac = null;
            }
          },
          onDOMReady: function onDOMReady() {
            Y = k(Z.remote);
            if (Z.isHost) {
              U(Z.props, {
                src: Q(Z.remote, {
                  xdm_e: q.protocol + "//" + q.host + q.pathname,
                  xdm_c: Z.channel,
                  xdm_p: 4
                }),
                name: V + Z.channel + "_provider"
              });
              ac = B(Z);
              p.Fn.set(Z.channel, function (ad) {
                ab = ad;
                L(function () {
                  aa.up.callback(true);
                }, 0);
                return function (ae) {
                  aa.up.incoming(ae, Y);
                };
              });
            } else {
              ab = n().Fn.get(Z.channel, true)(function (ad) {
                aa.up.incoming(ad, Y);
              });
              L(function () {
                aa.up.callback(true);
              }, 0);
            }
          },
          init: function init() {
            H(aa.onDOMReady, aa);
          }
        };
      };
      p.stack.FlashTransport = function (ab) {
        var ad, Y, ae, Z, af;
        function ag(ai, ah) {
          L(function () {
            ad.up.incoming(ai, ae);
          }, 0);
        }
        function aa(ai) {
          var ah = ab.swf + "?host=" + ab.isHost;
          var ak = "easyXDM_swf_" + Math.floor(Math.random() * 10000);
          p.Fn.set("flash_loaded" + ai.replace(/[\-.]/g, "_"), function () {
            p.stack.FlashTransport[ai].swf = Z = af.firstChild;
            var al = p.stack.FlashTransport[ai].queue;
            for (var am = 0; am < al.length; am++) {
              al[am]();
            }
            al.length = 0;
          });
          if (ab.swfContainer) {
            af = typeof ab.swfContainer == "string" ? d.getElementById(ab.swfContainer) : ab.swfContainer;
          } else {
            af = d.createElement("div");
            U(af.style, i && ab.swfNoThrottle ? {
              height: "20px",
              width: "20px",
              position: "fixed",
              right: 0,
              top: 0
            } : {
              height: "1px",
              width: "1px",
              position: "absolute",
              overflow: "hidden",
              right: 0,
              top: 0
            });
            d.body.appendChild(af);
          }
          var aj = "callback=flash_loaded" + I(ai.replace(/[\-.]/g, "_")) + "&proto=" + b.location.protocol + "&domain=" + I(A(b.location.href)) + "&port=" + I(g(b.location.href)) + "&ns=" + I(J);
          af.innerHTML = "<object height='20' width='20' type='application/x-shockwave-flash' id='" + ak + "' data='" + ah + "'><param name='allowScriptAccess' value='always'></param><param name='wmode' value='transparent'><param name='movie' value='" + ah + "'></param><param name='flashvars' value='" + aj + "'></param><embed type='application/x-shockwave-flash' FlashVars='" + aj + "' allowScriptAccess='always' wmode='transparent' src='" + ah + "' height='1' width='1'></embed></object>";
        }
        return ad = {
          outgoing: function outgoing(ai, aj, ah) {
            Z.postMessage(ab.channel, ai.toString());
            if (ah) {
              ah();
            }
          },
          destroy: function destroy() {
            try {
              Z.destroyChannel(ab.channel);
            } catch (ah) {}
            Z = null;
            if (Y) {
              Y.parentNode.removeChild(Y);
              Y = null;
            }
          },
          onDOMReady: function onDOMReady() {
            ae = ab.remote;
            p.Fn.set("flash_" + ab.channel + "_init", function () {
              L(function () {
                ad.up.callback(true);
              });
            });
            p.Fn.set("flash_" + ab.channel + "_onMessage", ag);
            ab.swf = C(ab.swf);
            var ai = A(ab.swf);
            var ah = function ah() {
              p.stack.FlashTransport[ai].init = true;
              Z = p.stack.FlashTransport[ai].swf;
              Z.createChannel(ab.channel, ab.secret, k(ab.remote), ab.isHost);
              if (ab.isHost) {
                if (i && ab.swfNoThrottle) {
                  U(ab.props, {
                    position: "fixed",
                    right: 0,
                    top: 0,
                    height: "20px",
                    width: "20px"
                  });
                }
                U(ab.props, {
                  src: Q(ab.remote, {
                    xdm_e: k(q.href),
                    xdm_c: ab.channel,
                    xdm_p: 6,
                    xdm_s: ab.secret
                  }),
                  name: V + ab.channel + "_provider"
                });
                Y = B(ab);
              }
            };
            if (p.stack.FlashTransport[ai] && p.stack.FlashTransport[ai].init) {
              ah();
            } else {
              if (!p.stack.FlashTransport[ai]) {
                p.stack.FlashTransport[ai] = {
                  queue: [ah]
                };
                aa(ai);
              } else {
                p.stack.FlashTransport[ai].queue.push(ah);
              }
            }
          },
          init: function init() {
            H(ad.onDOMReady, ad);
          }
        };
      };
      p.stack.PostMessageTransport = function (ac) {
        var ae, af, aa, ab;
        function Z(ag) {
          if (ag.origin) {
            return k(ag.origin);
          }
          if (ag.uri) {
            return k(ag.uri);
          }
          if (ag.domain) {
            return q.protocol + "//" + ag.domain;
          }
          throw "Unable to retrieve the origin of the event";
        }
        function ad(ah) {
          if (typeof ah.data !== "string") {
            return;
          }
          var ag = Z(ah);
          if (ag == ab && typeof ah.data === "string" && ah.data.substring(0, ac.channel.length + 1) == ac.channel + " ") {
            ae.up.incoming(ah.data.substring(ac.channel.length + 1), ag);
          }
        }
        function Y(ag) {
          if (ag.data == ac.channel + "-ready") {
            aa = "postMessage" in af.contentWindow ? af.contentWindow : af.contentWindow.document;
            y(O, "message", Y);
            w(O, "message", ad);
            L(function () {
              ae.up.callback(true);
            }, 0);
          }
        }
        return ae = {
          outgoing: function outgoing(ah, ai, ag) {
            aa.postMessage(ac.channel + " " + ah, ai || ab);
            if (ag) {
              ag();
            }
          },
          destroy: function destroy() {
            y(O, "message", Y);
            y(O, "message", ad);
            if (af) {
              aa = null;
              af.parentNode.removeChild(af);
              af = null;
            }
          },
          onDOMReady: function onDOMReady() {
            ab = k(ac.remote);
            if (ab === "file://") {
              ab = "*";
            }
            if (ac.isHost) {
              w(O, "message", Y);
              U(ac.props, {
                src: Q(ac.remote, {
                  xdm_e: k(q.href),
                  xdm_c: ac.channel,
                  xdm_p: 1
                }),
                name: V + ac.channel + "_provider"
              });
              af = B(ac);
            } else {
              w(O, "message", ad);
              aa = "postMessage" in O.parent ? O.parent : O.parent.document;
              aa.postMessage(ac.channel + "-ready", ab);
              L(function () {
                ae.up.callback(true);
              }, 0);
            }
          },
          init: function init() {
            H(ae.onDOMReady, ae);
          }
        };
      };
      p.stack.FrameElementTransport = function (Z) {
        var aa, ac, ab, Y;
        return aa = {
          outgoing: function outgoing(ae, af, ad) {
            ab.call(this, ae);
            if (ad) {
              ad();
            }
          },
          destroy: function destroy() {
            if (ac) {
              ac.parentNode.removeChild(ac);
              ac = null;
            }
          },
          onDOMReady: function onDOMReady() {
            Y = k(Z.remote);
            if (Z.isHost) {
              U(Z.props, {
                src: Q(Z.remote, {
                  xdm_e: k(q.href),
                  xdm_c: Z.channel,
                  xdm_p: 5
                }),
                name: V + Z.channel + "_provider"
              });
              ac = B(Z);
              ac.fn = function (ad) {
                delete ac.fn;
                ab = ad;
                L(function () {
                  aa.up.callback(true);
                }, 0);
                return function (ae) {
                  aa.up.incoming(ae, Y);
                };
              };
            } else {
              if (d.referrer && k(d.referrer) != T.xdm_e) {
                O.top.location = T.xdm_e;
              }
              ab = O.frameElement.fn(function (ad) {
                aa.up.incoming(ad, Y);
              });
              aa.up.callback(true);
            }
          },
          init: function init() {
            H(aa.onDOMReady, aa);
          }
        };
      };
      p.stack.NameTransport = function (ac) {
        var ad;
        var af, aj, ab, ah, ai, Z, Y;
        function ag(am) {
          var al = ac.remoteHelper + (af ? "#_3" : "#_2") + ac.channel;
          aj.contentWindow.sendMessage(am, al);
        }
        function ae() {
          if (af) {
            if (++ah === 2 || !af) {
              ad.up.callback(true);
            }
          } else {
            ag("ready");
            ad.up.callback(true);
          }
        }
        function ak(al) {
          ad.up.incoming(al, Z);
        }
        function aa() {
          if (ai) {
            L(function () {
              ai(true);
            }, 0);
          }
        }
        return ad = {
          outgoing: function outgoing(am, an, al) {
            ai = al;
            ag(am);
          },
          destroy: function destroy() {
            aj.parentNode.removeChild(aj);
            aj = null;
            if (af) {
              ab.parentNode.removeChild(ab);
              ab = null;
            }
          },
          onDOMReady: function onDOMReady() {
            af = ac.isHost;
            ah = 0;
            Z = k(ac.remote);
            ac.local = C(ac.local);
            if (af) {
              p.Fn.set(ac.channel, function (am) {
                if (af && am === "ready") {
                  p.Fn.set(ac.channel, ak);
                  ae();
                }
              });
              Y = Q(ac.remote, {
                xdm_e: ac.local,
                xdm_c: ac.channel,
                xdm_p: 2
              });
              U(ac.props, {
                src: Y + "#" + ac.channel,
                name: V + ac.channel + "_provider"
              });
              ab = B(ac);
            } else {
              ac.remoteHelper = ac.remote;
              p.Fn.set(ac.channel, ak);
            }
            var al = function al() {
              var am = aj || this;
              y(am, "load", al);
              p.Fn.set(ac.channel + "_load", aa);
              (function an() {
                if (typeof am.contentWindow.sendMessage == "function") {
                  ae();
                } else {
                  L(an, 50);
                }
              })();
            };
            aj = B({
              props: {
                src: ac.local + "#_4" + ac.channel
              },
              onLoad: al
            });
          },
          init: function init() {
            H(ad.onDOMReady, ad);
          }
        };
      };
      p.stack.HashTransport = function (aa) {
        var ad;
        var ag,
            ab,
            Y,
            ae,
            an,
            ac,
            am;
        var ah, Z;
        function al(ap) {
          if (!am) {
            return;
          }
          var ao = aa.remote + "#" + an++ + "_" + ap;
          (ag || !ah ? am.contentWindow : am).location = ao;
        }
        function af(ao) {
          ae = ao;
          ad.up.incoming(ae.substring(ae.indexOf("_") + 1), Z);
        }
        function ak() {
          if (!ac) {
            return;
          }
          var ao = ac.location.href,
              aq = "",
              ap = ao.indexOf("#");
          if (ap != -1) {
            aq = ao.substring(ap);
          }
          if (aq && aq != ae) {
            af(aq);
          }
        }
        function aj() {
          ab = setInterval(ak, Y);
        }
        return ad = {
          outgoing: function outgoing(ao, ap) {
            al(ao);
          },
          destroy: function destroy() {
            O.clearInterval(ab);
            if (ag || !ah) {
              am.parentNode.removeChild(am);
            }
            am = null;
          },
          onDOMReady: function onDOMReady() {
            ag = aa.isHost;
            Y = aa.interval;
            ae = "#" + aa.channel;
            an = 0;
            ah = aa.useParent;
            Z = k(aa.remote);
            if (ag) {
              U(aa.props, {
                src: aa.remote,
                name: V + aa.channel + "_provider"
              });
              if (ah) {
                aa.onLoad = function () {
                  ac = O;
                  aj();
                  ad.up.callback(true);
                };
              } else {
                var aq = 0,
                    ao = aa.delay / 50;
                (function ap() {
                  if (++aq > ao) {
                    throw new Error("Unable to reference listenerwindow");
                  }
                  try {
                    ac = am.contentWindow.frames[V + aa.channel + "_consumer"];
                  } catch (ar) {}
                  if (ac) {
                    aj();
                    ad.up.callback(true);
                  } else {
                    L(ap, 50);
                  }
                })();
              }
              am = B(aa);
            } else {
              ac = O;
              aj();
              if (ah) {
                am = parent;
                ad.up.callback(true);
              } else {
                U(aa, {
                  props: {
                    src: aa.remote + "#" + aa.channel + new Date(),
                    name: V + aa.channel + "_consumer"
                  },
                  onLoad: function onLoad() {
                    ad.up.callback(true);
                  }
                });
                am = B(aa);
              }
            }
          },
          init: function init() {
            H(ad.onDOMReady, ad);
          }
        };
      };
      p.stack.ReliableBehavior = function (Z) {
        var ab, ad;
        var ac = 0,
            Y = 0,
            aa = "";
        return ab = {
          incoming: function incoming(ag, ae) {
            var af = ag.indexOf("_"),
                ah = ag.substring(0, af).split(",");
            ag = ag.substring(af + 1);
            if (ah[0] == ac) {
              aa = "";
              if (ad) {
                ad(true);
              }
            }
            if (ag.length > 0) {
              ab.down.outgoing(ah[1] + "," + ac + "_" + aa, ae);
              if (Y != ah[1]) {
                Y = ah[1];
                ab.up.incoming(ag, ae);
              }
            }
          },
          outgoing: function outgoing(ag, ae, af) {
            aa = ag;
            ad = af;
            ab.down.outgoing(Y + "," + ++ac + "_" + ag, ae);
          }
        };
      };
      p.stack.QueueBehavior = function (aa) {
        var ad,
            ae = [],
            ah = true,
            ab = "",
            ag,
            Y = 0,
            Z = false,
            ac = false;
        function af() {
          if (aa.remove && ae.length === 0) {
            x(ad);
            return;
          }
          if (ah || ae.length === 0 || ag) {
            return;
          }
          ah = true;
          var ai = ae.shift();
          ad.down.outgoing(ai.data, ai.origin, function (aj) {
            ah = false;
            if (ai.callback) {
              L(function () {
                ai.callback(aj);
              }, 0);
            }
            af();
          });
        }
        return ad = {
          init: function init() {
            if (u(aa)) {
              aa = {};
            }
            if (aa.maxLength) {
              Y = aa.maxLength;
              ac = true;
            }
            if (aa.lazy) {
              Z = true;
            } else {
              ad.down.init();
            }
          },
          callback: function callback(aj) {
            ah = false;
            var ai = ad.up;
            af();
            ai.callback(aj);
          },
          incoming: function incoming(al, aj) {
            if (ac) {
              var ak = al.indexOf("_"),
                  ai = parseInt(al.substring(0, ak), 10);
              ab += al.substring(ak + 1);
              if (ai === 0) {
                if (aa.encode) {
                  ab = l(ab);
                }
                ad.up.incoming(ab, aj);
                ab = "";
              }
            } else {
              ad.up.incoming(al, aj);
            }
          },
          outgoing: function outgoing(am, aj, al) {
            if (aa.encode) {
              am = I(am);
            }
            var ai = [],
                ak;
            if (ac) {
              while (am.length !== 0) {
                ak = am.substring(0, Y);
                am = am.substring(ak.length);
                ai.push(ak);
              }
              while (ak = ai.shift()) {
                ae.push({
                  data: ai.length + "_" + ak,
                  origin: aj,
                  callback: ai.length === 0 ? al : null
                });
              }
            } else {
              ae.push({
                data: am,
                origin: aj,
                callback: al
              });
            }
            if (Z) {
              ad.down.init();
            } else {
              af();
            }
          },
          destroy: function destroy() {
            ag = true;
            ad.down.destroy();
          }
        };
      };
      p.stack.VerifyBehavior = function (ac) {
        var ad,
            ab,
            Z;
        function Y() {
          ab = Math.random().toString(16).substring(2);
          ad.down.outgoing(ab);
        }
        return ad = {
          incoming: function incoming(ag, ae) {
            var af = ag.indexOf("_");
            if (af === -1) {
              if (ag === ab) {
                ad.up.callback(true);
              } else {
                if (!Z) {
                  Z = ag;
                  if (!ac.initiate) {
                    Y();
                  }
                  ad.down.outgoing(ag);
                }
              }
            } else {
              if (ag.substring(0, af) === Z) {
                ad.up.incoming(ag.substring(af + 1), ae);
              }
            }
          },
          outgoing: function outgoing(ag, ae, af) {
            ad.down.outgoing(ab + "_" + ag, ae, af);
          },
          callback: function callback(ae) {
            if (ac.initiate) {
              Y();
            }
          }
        };
      };
      p.stack.RpcBehavior = function (ae, Z) {
        var ab,
            ag = Z.serializer || _P();
        var af = 0,
            ad = {};
        function Y(ah) {
          ah.jsonrpc = "2.0";
          ab.down.outgoing(ag.stringify(ah));
        }
        function ac(ah, aj) {
          var ai = Array.prototype.slice;
          return function () {
            var ak = arguments.length,
                am,
                al = {
              method: aj
            };
            if (ak > 0 && typeof arguments[ak - 1] === "function") {
              if (ak > 1 && typeof arguments[ak - 2] === "function") {
                am = {
                  success: arguments[ak - 2],
                  error: arguments[ak - 1]
                };
                al.params = ai.call(arguments, 0, ak - 2);
              } else {
                am = {
                  success: arguments[ak - 1]
                };
                al.params = ai.call(arguments, 0, ak - 1);
              }
              ad["" + ++af] = am;
              al.id = af;
            } else {
              al.params = ai.call(arguments, 0);
            }
            if (ah.namedParams && al.params.length === 1) {
              al.params = al.params[0];
            }
            Y(al);
          };
        }
        function aa(ah, aj, an, al) {
          if (!an) {
            if (aj) {
              Y({
                id: aj,
                error: {
                  code: -32601,
                  message: "Procedure not found."
                }
              });
            }
            return;
          }
          var _ao, _am;
          if (aj) {
            _ao = function ao(aq) {
              _ao = r;
              Y({
                id: aj,
                result: aq
              });
            };
            _am = function am(aq, ar) {
              _am = r;
              var at = {
                id: aj,
                error: {
                  code: -32099,
                  message: aq
                }
              };
              if (ar) {
                at.error.data = ar;
              }
              Y(at);
            };
          } else {
            _ao = _am = r;
          }
          if (!s(al)) {
            al = [al];
          }
          try {
            var ak = ae.context || an.scope;
            var ap = an.method.apply(ak, al.concat([_ao, _am]));
            if (!u(ap)) {
              _ao(ap);
            }
          } catch (ai) {
            _am(ai.message);
          }
        }
        return ab = {
          incoming: function incoming(ai, ah) {
            var aj = ag.parse(ai);
            if (aj.method) {
              if (Z.handle) {
                Z.handle(aj, Y);
              } else {
                aa(aj.method, aj.id, Z.local[aj.method], aj.params);
              }
            } else {
              var ak = ad[aj.id];
              if (aj.error) {
                if (ak.error) {
                  ak.error(aj.error);
                }
              } else {
                if (ak.success) {
                  ak.success(aj.result);
                }
              }
              delete ad[aj.id];
            }
          },
          init: function init() {
            if (Z.remote) {
              for (var ah in Z.remote) {
                if (Z.remote.hasOwnProperty(ah)) {
                  ae[ah] = ac(Z.remote[ah], ah);
                }
              }
            }
            ab.down.init();
          },
          destroy: function destroy() {
            for (var ah in Z.remote) {
              if (Z.remote.hasOwnProperty(ah) && ae.hasOwnProperty(ah)) {
                delete ae[ah];
              }
            }
            ab.down.destroy();
          }
        };
      };
      b.easyXDM = p;
    })(window, document, location, window.setTimeout, decodeURIComponent, encodeURIComponent);
    return easyXDM.noConflict('Kakao');
  }();

  var CryptoJS = function () {
    var CryptoJS = CryptoJS || function (u, p) {
      var d = {},
          l = d.lib = {},
          s = function s() {},
          t = l.Base = {
        extend: function extend(a) {
          s.prototype = this;
          var c = new s();
          a && c.mixIn(a);
          c.hasOwnProperty("init") || (c.init = function () {
            c.$super.init.apply(this, arguments);
          });
          c.init.prototype = c;
          c.$super = this;
          return c;
        },
        create: function create() {
          var a = this.extend();
          a.init.apply(a, arguments);
          return a;
        },
        init: function init() {},
        mixIn: function mixIn(a) {
          for (var c in a) {
            a.hasOwnProperty(c) && (this[c] = a[c]);
          }
          a.hasOwnProperty("toString") && (this.toString = a.toString);
        },
        clone: function clone() {
          return this.init.prototype.extend(this);
        }
      },
          r = l.WordArray = t.extend({
        init: function init(a, c) {
          a = this.words = a || [];
          this.sigBytes = c != p ? c : 4 * a.length;
        },
        toString: function toString(a) {
          return (a || v).stringify(this);
        },
        concat: function concat(a) {
          var c = this.words,
              e = a.words,
              j = this.sigBytes;
          a = a.sigBytes;
          this.clamp();
          if (j % 4) for (var k = 0; k < a; k++) {
            c[j + k >>> 2] |= (e[k >>> 2] >>> 24 - 8 * (k % 4) & 255) << 24 - 8 * ((j + k) % 4);
          } else if (65535 < e.length) for (k = 0; k < a; k += 4) {
            c[j + k >>> 2] = e[k >>> 2];
          } else c.push.apply(c, e);
          this.sigBytes += a;
          return this;
        },
        clamp: function clamp() {
          var a = this.words,
              c = this.sigBytes;
          a[c >>> 2] &= 4294967295 << 32 - 8 * (c % 4);
          a.length = u.ceil(c / 4);
        },
        clone: function clone() {
          var a = t.clone.call(this);
          a.words = this.words.slice(0);
          return a;
        },
        random: function random(a) {
          for (var c = [], e = 0; e < a; e += 4) {
            c.push(4294967296 * u.random() | 0);
          }
          return new r.init(c, a);
        }
      }),
          w = d.enc = {},
          v = w.Hex = {
        stringify: function stringify(a) {
          var c = a.words;
          a = a.sigBytes;
          for (var e = [], j = 0; j < a; j++) {
            var k = c[j >>> 2] >>> 24 - 8 * (j % 4) & 255;
            e.push((k >>> 4).toString(16));
            e.push((k & 15).toString(16));
          }
          return e.join("");
        },
        parse: function parse(a) {
          for (var c = a.length, e = [], j = 0; j < c; j += 2) {
            e[j >>> 3] |= parseInt(a.substr(j, 2), 16) << 24 - 4 * (j % 8);
          }
          return new r.init(e, c / 2);
        }
      },
          b = w.Latin1 = {
        stringify: function stringify(a) {
          var c = a.words;
          a = a.sigBytes;
          for (var e = [], j = 0; j < a; j++) {
            e.push(String.fromCharCode(c[j >>> 2] >>> 24 - 8 * (j % 4) & 255));
          }
          return e.join("");
        },
        parse: function parse(a) {
          for (var c = a.length, e = [], j = 0; j < c; j++) {
            e[j >>> 2] |= (a.charCodeAt(j) & 255) << 24 - 8 * (j % 4);
          }
          return new r.init(e, c);
        }
      },
          x = w.Utf8 = {
        stringify: function stringify(a) {
          try {
            return decodeURIComponent(escape(b.stringify(a)));
          } catch (c) {
            throw Error("Malformed UTF-8 data");
          }
        },
        parse: function parse(a) {
          return b.parse(unescape(encodeURIComponent(a)));
        }
      },
          q = l.BufferedBlockAlgorithm = t.extend({
        reset: function reset() {
          this._data = new r.init();
          this._nDataBytes = 0;
        },
        _append: function _append(a) {
          "string" == typeof a && (a = x.parse(a));
          this._data.concat(a);
          this._nDataBytes += a.sigBytes;
        },
        _process: function _process(a) {
          var c = this._data,
              e = c.words,
              j = c.sigBytes,
              k = this.blockSize,
              b = j / (4 * k),
              b = a ? u.ceil(b) : u.max((b | 0) - this._minBufferSize, 0);
          a = b * k;
          j = u.min(4 * a, j);
          if (a) {
            for (var q = 0; q < a; q += k) {
              this._doProcessBlock(e, q);
            }
            q = e.splice(0, a);
            c.sigBytes -= j;
          }
          return new r.init(q, j);
        },
        clone: function clone() {
          var a = t.clone.call(this);
          a._data = this._data.clone();
          return a;
        },
        _minBufferSize: 0
      });
      l.Hasher = q.extend({
        cfg: t.extend(),
        init: function init(a) {
          this.cfg = this.cfg.extend(a);
          this.reset();
        },
        reset: function reset() {
          q.reset.call(this);
          this._doReset();
        },
        update: function update(a) {
          this._append(a);
          this._process();
          return this;
        },
        finalize: function finalize(a) {
          a && this._append(a);
          return this._doFinalize();
        },
        blockSize: 16,
        _createHelper: function _createHelper(a) {
          return function (b, e) {
            return new a.init(e).finalize(b);
          };
        },
        _createHmacHelper: function _createHmacHelper(a) {
          return function (b, e) {
            return new n.HMAC.init(a, e).finalize(b);
          };
        }
      });
      var n = d.algo = {};
      return d;
    }(Math);
    (function () {
      var u = CryptoJS,
          p = u.lib.WordArray;
      u.enc.Base64 = {
        stringify: function stringify(d) {
          var l = d.words,
              p = d.sigBytes,
              t = this._map;
          d.clamp();
          d = [];
          for (var r = 0; r < p; r += 3) {
            for (var w = (l[r >>> 2] >>> 24 - 8 * (r % 4) & 255) << 16 | (l[r + 1 >>> 2] >>> 24 - 8 * ((r + 1) % 4) & 255) << 8 | l[r + 2 >>> 2] >>> 24 - 8 * ((r + 2) % 4) & 255, v = 0; 4 > v && r + 0.75 * v < p; v++) {
              d.push(t.charAt(w >>> 6 * (3 - v) & 63));
            }
          }
          if (l = t.charAt(64)) for (; d.length % 4;) {
            d.push(l);
          }
          return d.join("");
        },
        parse: function parse(d) {
          var l = d.length,
              s = this._map,
              t = s.charAt(64);
          t && (t = d.indexOf(t), -1 != t && (l = t));
          for (var t = [], r = 0, w = 0; w < l; w++) {
            if (w % 4) {
              var v = s.indexOf(d.charAt(w - 1)) << 2 * (w % 4),
                  b = s.indexOf(d.charAt(w)) >>> 6 - 2 * (w % 4);
              t[r >>> 2] |= (v | b) << 24 - 8 * (r % 4);
              r++;
            }
          }
          return p.create(t, r);
        },
        _map: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="
      };
    })();
    (function (u) {
      function p(b, n, a, c, e, j, k) {
        b = b + (n & a | ~n & c) + e + k;
        return (b << j | b >>> 32 - j) + n;
      }
      function d(b, n, a, c, e, j, k) {
        b = b + (n & c | a & ~c) + e + k;
        return (b << j | b >>> 32 - j) + n;
      }
      function l(b, n, a, c, e, j, k) {
        b = b + (n ^ a ^ c) + e + k;
        return (b << j | b >>> 32 - j) + n;
      }
      function s(b, n, a, c, e, j, k) {
        b = b + (a ^ (n | ~c)) + e + k;
        return (b << j | b >>> 32 - j) + n;
      }
      for (var t = CryptoJS, r = t.lib, w = r.WordArray, v = r.Hasher, r = t.algo, b = [], x = 0; 64 > x; x++) {
        b[x] = 4294967296 * u.abs(u.sin(x + 1)) | 0;
      }
      r = r.MD5 = v.extend({
        _doReset: function _doReset() {
          this._hash = new w.init([1732584193, 4023233417, 2562383102, 271733878]);
        },
        _doProcessBlock: function _doProcessBlock(q, n) {
          for (var a = 0; 16 > a; a++) {
            var c = n + a,
                e = q[c];
            q[c] = (e << 8 | e >>> 24) & 16711935 | (e << 24 | e >>> 8) & 4278255360;
          }
          var a = this._hash.words,
              c = q[n + 0],
              e = q[n + 1],
              j = q[n + 2],
              k = q[n + 3],
              z = q[n + 4],
              r = q[n + 5],
              t = q[n + 6],
              w = q[n + 7],
              v = q[n + 8],
              A = q[n + 9],
              B = q[n + 10],
              C = q[n + 11],
              u = q[n + 12],
              D = q[n + 13],
              E = q[n + 14],
              x = q[n + 15],
              f = a[0],
              m = a[1],
              g = a[2],
              h = a[3],
              f = p(f, m, g, h, c, 7, b[0]),
              h = p(h, f, m, g, e, 12, b[1]),
              g = p(g, h, f, m, j, 17, b[2]),
              m = p(m, g, h, f, k, 22, b[3]),
              f = p(f, m, g, h, z, 7, b[4]),
              h = p(h, f, m, g, r, 12, b[5]),
              g = p(g, h, f, m, t, 17, b[6]),
              m = p(m, g, h, f, w, 22, b[7]),
              f = p(f, m, g, h, v, 7, b[8]),
              h = p(h, f, m, g, A, 12, b[9]),
              g = p(g, h, f, m, B, 17, b[10]),
              m = p(m, g, h, f, C, 22, b[11]),
              f = p(f, m, g, h, u, 7, b[12]),
              h = p(h, f, m, g, D, 12, b[13]),
              g = p(g, h, f, m, E, 17, b[14]),
              m = p(m, g, h, f, x, 22, b[15]),
              f = d(f, m, g, h, e, 5, b[16]),
              h = d(h, f, m, g, t, 9, b[17]),
              g = d(g, h, f, m, C, 14, b[18]),
              m = d(m, g, h, f, c, 20, b[19]),
              f = d(f, m, g, h, r, 5, b[20]),
              h = d(h, f, m, g, B, 9, b[21]),
              g = d(g, h, f, m, x, 14, b[22]),
              m = d(m, g, h, f, z, 20, b[23]),
              f = d(f, m, g, h, A, 5, b[24]),
              h = d(h, f, m, g, E, 9, b[25]),
              g = d(g, h, f, m, k, 14, b[26]),
              m = d(m, g, h, f, v, 20, b[27]),
              f = d(f, m, g, h, D, 5, b[28]),
              h = d(h, f, m, g, j, 9, b[29]),
              g = d(g, h, f, m, w, 14, b[30]),
              m = d(m, g, h, f, u, 20, b[31]),
              f = l(f, m, g, h, r, 4, b[32]),
              h = l(h, f, m, g, v, 11, b[33]),
              g = l(g, h, f, m, C, 16, b[34]),
              m = l(m, g, h, f, E, 23, b[35]),
              f = l(f, m, g, h, e, 4, b[36]),
              h = l(h, f, m, g, z, 11, b[37]),
              g = l(g, h, f, m, w, 16, b[38]),
              m = l(m, g, h, f, B, 23, b[39]),
              f = l(f, m, g, h, D, 4, b[40]),
              h = l(h, f, m, g, c, 11, b[41]),
              g = l(g, h, f, m, k, 16, b[42]),
              m = l(m, g, h, f, t, 23, b[43]),
              f = l(f, m, g, h, A, 4, b[44]),
              h = l(h, f, m, g, u, 11, b[45]),
              g = l(g, h, f, m, x, 16, b[46]),
              m = l(m, g, h, f, j, 23, b[47]),
              f = s(f, m, g, h, c, 6, b[48]),
              h = s(h, f, m, g, w, 10, b[49]),
              g = s(g, h, f, m, E, 15, b[50]),
              m = s(m, g, h, f, r, 21, b[51]),
              f = s(f, m, g, h, u, 6, b[52]),
              h = s(h, f, m, g, k, 10, b[53]),
              g = s(g, h, f, m, B, 15, b[54]),
              m = s(m, g, h, f, e, 21, b[55]),
              f = s(f, m, g, h, v, 6, b[56]),
              h = s(h, f, m, g, x, 10, b[57]),
              g = s(g, h, f, m, t, 15, b[58]),
              m = s(m, g, h, f, D, 21, b[59]),
              f = s(f, m, g, h, z, 6, b[60]),
              h = s(h, f, m, g, C, 10, b[61]),
              g = s(g, h, f, m, j, 15, b[62]),
              m = s(m, g, h, f, A, 21, b[63]);
          a[0] = a[0] + f | 0;
          a[1] = a[1] + m | 0;
          a[2] = a[2] + g | 0;
          a[3] = a[3] + h | 0;
        },
        _doFinalize: function _doFinalize() {
          var b = this._data,
              n = b.words,
              a = 8 * this._nDataBytes,
              c = 8 * b.sigBytes;
          n[c >>> 5] |= 128 << 24 - c % 32;
          var e = u.floor(a / 4294967296);
          n[(c + 64 >>> 9 << 4) + 15] = (e << 8 | e >>> 24) & 16711935 | (e << 24 | e >>> 8) & 4278255360;
          n[(c + 64 >>> 9 << 4) + 14] = (a << 8 | a >>> 24) & 16711935 | (a << 24 | a >>> 8) & 4278255360;
          b.sigBytes = 4 * (n.length + 1);
          this._process();
          b = this._hash;
          n = b.words;
          for (a = 0; 4 > a; a++) {
            c = n[a], n[a] = (c << 8 | c >>> 24) & 16711935 | (c << 24 | c >>> 8) & 4278255360;
          }
          return b;
        },
        clone: function clone() {
          var b = v.clone.call(this);
          b._hash = this._hash.clone();
          return b;
        }
      });
      t.MD5 = v._createHelper(r);
      t.HmacMD5 = v._createHmacHelper(r);
    })(Math);
    (function () {
      var u = CryptoJS,
          p = u.lib,
          d = p.Base,
          l = p.WordArray,
          p = u.algo,
          s = p.EvpKDF = d.extend({
        cfg: d.extend({
          keySize: 4,
          hasher: p.MD5,
          iterations: 1
        }),
        init: function init(d) {
          this.cfg = this.cfg.extend(d);
        },
        compute: function compute(d, r) {
          for (var p = this.cfg, s = p.hasher.create(), b = l.create(), u = b.words, q = p.keySize, p = p.iterations; u.length < q;) {
            n && s.update(n);
            var n = s.update(d).finalize(r);
            s.reset();
            for (var a = 1; a < p; a++) {
              n = s.finalize(n), s.reset();
            }
            b.concat(n);
          }
          b.sigBytes = 4 * q;
          return b;
        }
      });
      u.EvpKDF = function (d, l, p) {
        return s.create(p).compute(d, l);
      };
    })();
    CryptoJS.lib.Cipher || function (u) {
      var p = CryptoJS,
          d = p.lib,
          l = d.Base,
          s = d.WordArray,
          t = d.BufferedBlockAlgorithm,
          r = p.enc.Base64,
          w = p.algo.EvpKDF,
          v = d.Cipher = t.extend({
        cfg: l.extend(),
        createEncryptor: function createEncryptor(e, a) {
          return this.create(this._ENC_XFORM_MODE, e, a);
        },
        createDecryptor: function createDecryptor(e, a) {
          return this.create(this._DEC_XFORM_MODE, e, a);
        },
        init: function init(e, a, b) {
          this.cfg = this.cfg.extend(b);
          this._xformMode = e;
          this._key = a;
          this.reset();
        },
        reset: function reset() {
          t.reset.call(this);
          this._doReset();
        },
        process: function process(e) {
          this._append(e);
          return this._process();
        },
        finalize: function finalize(e) {
          e && this._append(e);
          return this._doFinalize();
        },
        keySize: 4,
        ivSize: 4,
        _ENC_XFORM_MODE: 1,
        _DEC_XFORM_MODE: 2,
        _createHelper: function _createHelper(e) {
          return {
            encrypt: function encrypt(b, k, d) {
              return ("string" == typeof k ? c : a).encrypt(e, b, k, d);
            },
            decrypt: function decrypt(b, k, d) {
              return ("string" == typeof k ? c : a).decrypt(e, b, k, d);
            }
          };
        }
      });
      d.StreamCipher = v.extend({
        _doFinalize: function _doFinalize() {
          return this._process(!0);
        },
        blockSize: 1
      });
      var b = p.mode = {},
          x = function x(e, a, b) {
        var c = this._iv;
        c ? this._iv = u : c = this._prevBlock;
        for (var d = 0; d < b; d++) {
          e[a + d] ^= c[d];
        }
      },
          q = (d.BlockCipherMode = l.extend({
        createEncryptor: function createEncryptor(e, a) {
          return this.Encryptor.create(e, a);
        },
        createDecryptor: function createDecryptor(e, a) {
          return this.Decryptor.create(e, a);
        },
        init: function init(e, a) {
          this._cipher = e;
          this._iv = a;
        }
      })).extend();
      q.Encryptor = q.extend({
        processBlock: function processBlock(e, a) {
          var b = this._cipher,
              c = b.blockSize;
          x.call(this, e, a, c);
          b.encryptBlock(e, a);
          this._prevBlock = e.slice(a, a + c);
        }
      });
      q.Decryptor = q.extend({
        processBlock: function processBlock(e, a) {
          var b = this._cipher,
              c = b.blockSize,
              d = e.slice(a, a + c);
          b.decryptBlock(e, a);
          x.call(this, e, a, c);
          this._prevBlock = d;
        }
      });
      b = b.CBC = q;
      q = (p.pad = {}).Pkcs7 = {
        pad: function pad(a, b) {
          for (var c = 4 * b, c = c - a.sigBytes % c, d = c << 24 | c << 16 | c << 8 | c, l = [], n = 0; n < c; n += 4) {
            l.push(d);
          }
          c = s.create(l, c);
          a.concat(c);
        },
        unpad: function unpad(a) {
          a.sigBytes -= a.words[a.sigBytes - 1 >>> 2] & 255;
        }
      };
      d.BlockCipher = v.extend({
        cfg: v.cfg.extend({
          mode: b,
          padding: q
        }),
        reset: function reset() {
          v.reset.call(this);
          var a = this.cfg,
              b = a.iv,
              a = a.mode;
          if (this._xformMode == this._ENC_XFORM_MODE) var c = a.createEncryptor;else c = a.createDecryptor, this._minBufferSize = 1;
          this._mode = c.call(a, this, b && b.words);
        },
        _doProcessBlock: function _doProcessBlock(a, b) {
          this._mode.processBlock(a, b);
        },
        _doFinalize: function _doFinalize() {
          var a = this.cfg.padding;
          if (this._xformMode == this._ENC_XFORM_MODE) {
            a.pad(this._data, this.blockSize);
            var b = this._process(!0);
          } else b = this._process(!0), a.unpad(b);
          return b;
        },
        blockSize: 4
      });
      var n = d.CipherParams = l.extend({
        init: function init(a) {
          this.mixIn(a);
        },
        toString: function toString(a) {
          return (a || this.formatter).stringify(this);
        }
      }),
          b = (p.format = {}).OpenSSL = {
        stringify: function stringify(a) {
          var b = a.ciphertext;
          a = a.salt;
          return (a ? s.create([1398893684, 1701076831]).concat(a).concat(b) : b).toString(r);
        },
        parse: function parse(a) {
          a = r.parse(a);
          var b = a.words;
          if (1398893684 == b[0] && 1701076831 == b[1]) {
            var c = s.create(b.slice(2, 4));
            b.splice(0, 4);
            a.sigBytes -= 16;
          }
          return n.create({
            ciphertext: a,
            salt: c
          });
        }
      },
          a = d.SerializableCipher = l.extend({
        cfg: l.extend({
          format: b
        }),
        encrypt: function encrypt(a, b, c, d) {
          d = this.cfg.extend(d);
          var l = a.createEncryptor(c, d);
          b = l.finalize(b);
          l = l.cfg;
          return n.create({
            ciphertext: b,
            key: c,
            iv: l.iv,
            algorithm: a,
            mode: l.mode,
            padding: l.padding,
            blockSize: a.blockSize,
            formatter: d.format
          });
        },
        decrypt: function decrypt(a, b, c, d) {
          d = this.cfg.extend(d);
          b = this._parse(b, d.format);
          return a.createDecryptor(c, d).finalize(b.ciphertext);
        },
        _parse: function _parse(a, b) {
          return "string" == typeof a ? b.parse(a, this) : a;
        }
      }),
          p = (p.kdf = {}).OpenSSL = {
        execute: function execute(a, b, c, d) {
          d || (d = s.random(8));
          a = w.create({
            keySize: b + c
          }).compute(a, d);
          c = s.create(a.words.slice(b), 4 * c);
          a.sigBytes = 4 * b;
          return n.create({
            key: a,
            iv: c,
            salt: d
          });
        }
      },
          c = d.PasswordBasedCipher = a.extend({
        cfg: a.cfg.extend({
          kdf: p
        }),
        encrypt: function encrypt(b, c, d, l) {
          l = this.cfg.extend(l);
          d = l.kdf.execute(d, b.keySize, b.ivSize);
          l.iv = d.iv;
          b = a.encrypt.call(this, b, c, d.key, l);
          b.mixIn(d);
          return b;
        },
        decrypt: function decrypt(b, c, d, l) {
          l = this.cfg.extend(l);
          c = this._parse(c, l.format);
          d = l.kdf.execute(d, b.keySize, b.ivSize, c.salt);
          l.iv = d.iv;
          return a.decrypt.call(this, b, c, d.key, l);
        }
      });
    }();
    (function () {
      for (var u = CryptoJS, p = u.lib.BlockCipher, d = u.algo, l = [], s = [], t = [], r = [], w = [], v = [], b = [], x = [], q = [], n = [], a = [], c = 0; 256 > c; c++) {
        a[c] = 128 > c ? c << 1 : c << 1 ^ 283;
      }
      for (var e = 0, j = 0, c = 0; 256 > c; c++) {
        var k = j ^ j << 1 ^ j << 2 ^ j << 3 ^ j << 4,
            k = k >>> 8 ^ k & 255 ^ 99;
        l[e] = k;
        s[k] = e;
        var z = a[e],
            F = a[z],
            G = a[F],
            y = 257 * a[k] ^ 16843008 * k;
        t[e] = y << 24 | y >>> 8;
        r[e] = y << 16 | y >>> 16;
        w[e] = y << 8 | y >>> 24;
        v[e] = y;
        y = 16843009 * G ^ 65537 * F ^ 257 * z ^ 16843008 * e;
        b[k] = y << 24 | y >>> 8;
        x[k] = y << 16 | y >>> 16;
        q[k] = y << 8 | y >>> 24;
        n[k] = y;
        e ? (e = z ^ a[a[a[G ^ z]]], j ^= a[a[j]]) : e = j = 1;
      }
      var H = [0, 1, 2, 4, 8, 16, 32, 64, 128, 27, 54],
          d = d.AES = p.extend({
        _doReset: function _doReset() {
          for (var a = this._key, c = a.words, d = a.sigBytes / 4, a = 4 * ((this._nRounds = d + 6) + 1), e = this._keySchedule = [], j = 0; j < a; j++) {
            if (j < d) e[j] = c[j];else {
              var k = e[j - 1];
              j % d ? 6 < d && 4 == j % d && (k = l[k >>> 24] << 24 | l[k >>> 16 & 255] << 16 | l[k >>> 8 & 255] << 8 | l[k & 255]) : (k = k << 8 | k >>> 24, k = l[k >>> 24] << 24 | l[k >>> 16 & 255] << 16 | l[k >>> 8 & 255] << 8 | l[k & 255], k ^= H[j / d | 0] << 24);
              e[j] = e[j - d] ^ k;
            }
          }
          c = this._invKeySchedule = [];
          for (d = 0; d < a; d++) {
            j = a - d, k = d % 4 ? e[j] : e[j - 4], c[d] = 4 > d || 4 >= j ? k : b[l[k >>> 24]] ^ x[l[k >>> 16 & 255]] ^ q[l[k >>> 8 & 255]] ^ n[l[k & 255]];
          }
        },
        encryptBlock: function encryptBlock(a, b) {
          this._doCryptBlock(a, b, this._keySchedule, t, r, w, v, l);
        },
        decryptBlock: function decryptBlock(a, c) {
          var d = a[c + 1];
          a[c + 1] = a[c + 3];
          a[c + 3] = d;
          this._doCryptBlock(a, c, this._invKeySchedule, b, x, q, n, s);
          d = a[c + 1];
          a[c + 1] = a[c + 3];
          a[c + 3] = d;
        },
        _doCryptBlock: function _doCryptBlock(a, b, c, d, e, j, l, f) {
          for (var m = this._nRounds, g = a[b] ^ c[0], h = a[b + 1] ^ c[1], k = a[b + 2] ^ c[2], n = a[b + 3] ^ c[3], p = 4, r = 1; r < m; r++) {
            var q = d[g >>> 24] ^ e[h >>> 16 & 255] ^ j[k >>> 8 & 255] ^ l[n & 255] ^ c[p++],
                s = d[h >>> 24] ^ e[k >>> 16 & 255] ^ j[n >>> 8 & 255] ^ l[g & 255] ^ c[p++],
                t = d[k >>> 24] ^ e[n >>> 16 & 255] ^ j[g >>> 8 & 255] ^ l[h & 255] ^ c[p++],
                n = d[n >>> 24] ^ e[g >>> 16 & 255] ^ j[h >>> 8 & 255] ^ l[k & 255] ^ c[p++],
                g = q,
                h = s,
                k = t;
          }
          q = (f[g >>> 24] << 24 | f[h >>> 16 & 255] << 16 | f[k >>> 8 & 255] << 8 | f[n & 255]) ^ c[p++];
          s = (f[h >>> 24] << 24 | f[k >>> 16 & 255] << 16 | f[n >>> 8 & 255] << 8 | f[g & 255]) ^ c[p++];
          t = (f[k >>> 24] << 24 | f[n >>> 16 & 255] << 16 | f[g >>> 8 & 255] << 8 | f[h & 255]) ^ c[p++];
          n = (f[n >>> 24] << 24 | f[g >>> 16 & 255] << 16 | f[h >>> 8 & 255] << 8 | f[k & 255]) ^ c[p++];
          a[b] = q;
          a[b + 1] = s;
          a[b + 2] = t;
          a[b + 3] = n;
        },
        keySize: 8
      });
      u.AES = p._createHelper(d);
    })();
    return CryptoJS;
  }();

  function getAppKey() {
    return getAppKey$1();
  }
  var accessToken$1 = null;
  function getAccessToken() {
    if (accessToken$1 === null) {
      accessToken$1 = retrieveItem(getAccessTokenKey());
    }
    return accessToken$1;
  }
  function setAccessToken(token) {
    var persist = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : true;
    accessToken$1 = token;
    if (token === null || persist === false) {
      removeItem(getAccessTokenKey());
    } else {
      storeItem(getAccessTokenKey(), token);
    }
  }
  function getRefreshToken() {
    console.error('unsupported operation: Auth.getRefreshToken()');
    return '';
  }
  function setRefreshToken(token) {
    console.error('unsupported operation: Auth.setRefreshToken()');
  }
  function storeItem(key, value) {
    var item = encrypt(value, getAppKey());
    localStorage.setItem(key, item);
  }
  function retrieveItem(key) {
    var item = localStorage.getItem(key);
    return item ? decrypt(item, getAppKey()) : null;
  }
  function removeItem(key) {
    localStorage.removeItem(key);
  }
  var tokenStorageKeys = {};
  function getAccessTokenKey() {
    if (!tokenStorageKeys.accessTokenKey) {
      tokenStorageKeys.accessTokenKey = 'kakao_' + hash('kat' + getAppKey());
    }
    return tokenStorageKeys.accessTokenKey;
  }
  function hash(msg) {
    return CryptoJS.MD5(msg).toString();
  }
  function encrypt(msg, passphrase) {
    return CryptoJS.AES.encrypt(msg, passphrase).toString();
  }
  function decrypt(encrypted, passphrase) {
    return CryptoJS.AES.decrypt(encrypted, passphrase).toString(CryptoJS.enc.Utf8);
  }

  var secret = /*#__PURE__*/Object.freeze({
    __proto__: null,
    getAppKey: getAppKey,
    getAccessToken: getAccessToken,
    setAccessToken: setAccessToken,
    getRefreshToken: getRefreshToken,
    setRefreshToken: setRefreshToken
  });

  function accessToken() {
    return "Bearer ".concat(getAccessToken());
  }
  function appKey() {
    return "KakaoAK ".concat(getAppKey());
  }

  var postApiCommonParams = {
    permission: isOneOf(['A', 'F', 'M']),
    enable_share: isBoolean,
    android_exec_param: isString,
    ios_exec_param: isString,
    android_market_param: isString,
    ios_market_param: isString
  };
  var secureResource = {
    secure_resource: isBoolean
  };
  function forceSecureResource(settings) {
    if (settings.secure_resource === false) {
      if (console) {
        console.warn('KakaoWarning: The secure_resource parameter is deprecated.');
      }
      settings.secure_resource = true;
    }
  }
  function storyActivityContentValidator(obj) {
    if (!isString(obj)) {
      return false;
    }
    if (obj.length === 0 || obj.length > 2048) {
      throw new KakaoError('content length should be between 0 and 2048');
    }
    return true;
  }
  function kageImageUrlListValidator(obj) {
    if (!isArray(obj)) {
      return false;
    }
    return every(obj, function (path) {
      if (!isString(path)) {
        return false;
      }
      if (isURL(path)) {
        throw new KakaoError("url in image_url_list should be a kage url, obtained from '/v1/api/story/upload/multi'.");
      }
      return true;
    });
  }
  function hasHeaderBackgroundImage(obj) {
    if (obj.header_image_url || obj.header_image_width || obj.header_image_height) {
      delete obj.header_image_url;
      delete obj.header_image_width;
      delete obj.header_image_height;
      if (console) {
        console.warn("KakaoWarning: The parameters (".concat(['header_image_url', 'header_image_width', 'header_image_height'].join(', '), ") for header background image are deprecated."));
      }
    }
    return true;
  }
  var apiRules = {
    '/v1/user/signup': {
      method: 'post',
      data: {
        optional: {
          properties: isObject
        }
      }
    },
    '/v1/user/unlink': {
      method: 'post'
    },
    '/v2/user/me': {
      method: 'get',
      data: {
        optional: assignIn({
          property_keys: isArray
        }, secureResource)
      }
    },
    '/v1/user/logout': {
      method: 'post',
      data: {}
    },
    '/v1/user/update_profile': {
      method: 'post',
      data: {
        required: {
          properties: isObject
        }
      }
    },
    '/v1/user/access_token_info': {
      method: 'get',
      data: {}
    },
    '/v2/user/scopes': {
      method: 'get',
      data: {
        optional: {
          scopes: isArray
        }
      }
    },
    '/v2/user/revoke/scopes': {
      method: 'post',
      data: {
        required: {
          scopes: isArray
        }
      }
    },
    '/v1/user/service/terms': {
      method: 'get'
    },
    '/v1/user/shipping_address': {
      method: 'get',
      data: {
        optional: {
          address_id: isInteger,
          from_updated_at: isInteger,
          page_size: isInteger
        }
      }
    },
    '/v1/api/talk/profile': {
      method: 'get',
      data: {
        optional: secureResource,
        after: forceSecureResource
      }
    },
    '/v1/api/talk/friends': {
      method: 'get',
      data: {
        optional: assignIn({
          offset: isInteger,
          limit: isInteger,
          order: isString
        }, secureResource),
        after: forceSecureResource
      }
    },
    '/v1/friends': {
      method: 'get',
      data: {
        optional: assignIn({
          offset: isInteger,
          limit: isInteger,
          order: isString,
          friend_order: isString
        }, secureResource),
        after: forceSecureResource
      }
    },
    '/v2/api/talk/memo/send': {
      method: 'post',
      data: {
        required: {
          template_id: isInteger
        },
        optional: {
          template_args: isObject
        }
      }
    },
    '/v2/api/talk/memo/scrap/send': {
      method: 'post',
      data: {
        required: {
          request_url: isString
        },
        optional: {
          template_id: isInteger,
          template_args: isObject
        }
      }
    },
    '/v2/api/talk/memo/default/send': {
      method: 'post',
      data: {
        required: {
          template_object: function template_object(obj) {
            return isObject(obj) && hasHeaderBackgroundImage(obj);
          }
        }
      }
    },
    '/v1/api/talk/friends/message/send': {
      method: 'post',
      data: {
        required: {
          template_id: isInteger,
          receiver_uuids: isArray,
          receiver_id_type: isString
        },
        optional: {
          template_args: isObject
        },
        defaults: function defaults() {
          return {
            receiver_id_type: 'uuid'
          };
        }
      }
    },
    '/v1/api/talk/friends/message/scrap/send': {
      method: 'post',
      data: {
        required: {
          request_url: isString,
          receiver_uuids: isArray,
          receiver_id_type: isString
        },
        optional: {
          template_id: isInteger,
          template_args: isObject
        },
        defaults: function defaults() {
          return {
            receiver_id_type: 'uuid'
          };
        }
      }
    },
    '/v1/api/talk/friends/message/default/send': {
      method: 'post',
      data: {
        required: {
          template_object: function template_object(obj) {
            return isObject(obj) && hasHeaderBackgroundImage(obj);
          },
          receiver_uuids: isArray,
          receiver_id_type: isString
        },
        defaults: function defaults() {
          return {
            receiver_id_type: 'uuid'
          };
        }
      }
    },
    '/v2/api/kakaolink/talk/template/validate': {
      method: 'get',
      data: {
        required: {
          link_ver: isString,
          template_id: isInteger
        },
        optional: {
          template_args: isObject
        }
      },
      authType: appKey
    },
    '/v2/api/kakaolink/talk/template/scrap': {
      method: 'get',
      data: {
        required: {
          link_ver: isString,
          request_url: isString
        },
        optional: {
          template_id: isInteger,
          template_args: isObject
        }
      },
      authType: appKey
    },
    '/v2/api/kakaolink/talk/template/default': {
      method: 'get',
      data: {
        required: {
          link_ver: isString,
          template_object: isObject
        }
      },
      authType: appKey
    },
    '/v2/api/talk/message/image/upload': {
      method: 'post',
      data: {
        required: {
          file: isObject
        }
      },
      authType: appKey
    },
    '/v2/api/talk/message/image/delete': {
      method: 'delete',
      data: {
        required: {
          image_url: isString
        }
      },
      authType: appKey
    },
    '/v2/api/talk/message/image/scrap': {
      method: 'post',
      data: {
        required: {
          image_url: isString
        }
      },
      authType: appKey
    },
    '/v1/api/story/profile': {
      method: 'get',
      data: {
        optional: secureResource
      }
    },
    '/v1/api/story/isstoryuser': {
      method: 'get'
    },
    '/v1/api/story/mystory': {
      method: 'get',
      data: {
        required: {
          id: isString
        }
      }
    },
    '/v1/api/story/mystories': {
      method: 'get',
      data: {
        optional: {
          last_id: isString
        }
      }
    },
    '/v1/api/story/linkinfo': {
      method: 'get',
      data: {
        required: {
          url: isString
        }
      }
    },
    '/v1/api/story/post/note': {
      method: 'post',
      data: {
        required: {
          content: storyActivityContentValidator
        },
        optional: postApiCommonParams
      }
    },
    '/v1/api/story/post/photo': {
      method: 'post',
      data: {
        required: {
          image_url_list: kageImageUrlListValidator
        },
        optional: assignIn({
          content: storyActivityContentValidator
        }, postApiCommonParams)
      }
    },
    '/v1/api/story/post/link': {
      method: 'post',
      data: {
        required: {
          link_info: isObject
        },
        optional: assignIn({
          content: storyActivityContentValidator
        }, postApiCommonParams)
      }
    },
    '/v1/api/story/upload/multi': {
      method: 'post',
      data: {}
    },
    '/v1/api/story/delete/mystory': {
      method: 'delete',
      data: {
        required: {
          id: isString
        }
      }
    },
    '/v1/api/talk/channels': {
      method: 'get',
      data: {
        optional: {
          channel_public_ids: isArray
        }
      }
    },
    '/v1/api/talk/plusfriends': {
      method: 'get',
      data: {
        optional: {
          plus_friend_public_ids: isArray
        }
      }
    }
  };
  var rules$7 = {
    request: {
      required: {
        url: function url(_url) {
          return isOneOf(keys(apiRules))(_url);
        }
      },
      optional: {
        data: isObject,
        files: function files(obj) {
          return passesOneOf([isArray, isFileList])(obj) && every(obj, passesOneOf([isFile, isBlob]));
        },
        file: isFile,
        success: isFunction,
        fail: isFunction,
        always: isFunction
      },
      defaults: {
        data: {},
        success: emptyFunc,
        fail: emptyFunc,
        always: emptyFunc
      }
    },
    api: apiRules
  };

  var proxyForRequest = null;
  function request$5(settings) {
    settings = processRules(settings, rules$7.request, 'API.request');
    var url = settings.url;
    var urlRule = rules$7.api[url].data;
    if (urlRule) {
      settings.data = processRules(settings.data, urlRule, "API.request - ".concat(url));
    }
    if (!proxyForRequest) {
      proxyForRequest = getProxy$1();
      cleanups$7.push(function () {
        proxyForRequest.destroy();
        proxyForRequest = null;
      });
    }
    return new es6Promise.Promise(function (resolve, reject) {
      getConfig(settings).then(function (config) {
        proxyForRequest.request(config, function (res) {
          settings.success(res);
          settings.always(res);
          resolve(res);
        }, function (xdmError) {
          var error = parseXdmError(xdmError);
          settings.fail(error);
          settings.always(error);
          reject(error);
        });
      }, function (error) {
        reject(error);
      });
    });
  }
  function getProxy$1() {
    return guardCreateEasyXDM(function () {
      return new easyXDM_1.Rpc({
        remote: URL.apiRemote
      }, {
        remote: {
          request: {}
        }
      });
    });
  }
  function parseXdmError(xdmError) {
    try {
      logDebug(xdmError);
      return JSON.parse(xdmError.message.responseText);
    } catch (e) {
      return {
        code: -777,
        msg: 'Unknown error'
      };
    }
  }
  function getConfig(settings) {
    var url = settings.url;
    var urlSpec = rules$7.api[url];
    var stringifiedData = {};
    forEach(settings.data, function (value, key) {
      stringifiedData[key] = isString(value) ? value : JSON.stringify(value);
    });
    var config = {
      url: url,
      method: urlSpec.method,
      headers: {
        KA: KAKAO_AGENT,
        Authorization: (urlSpec.authType || accessToken)(),
        'Cache-Control': 'no-cache',
        Pragma: 'no-cache'
      },
      data: stringifiedData
    };
    return new es6Promise.Promise(function (resolve, reject) {
      if (isFileRequired(url) || settings.data.file) {
        var files = settings.files || settings.data.file;
        if (!files) {
          throw new KakaoError("'files' parameter should be set for ".concat(url));
        }
        getFileConfig(files).then(function (fileConfig) {
          var searchParams = [];
          for (var prop in stringifiedData) {
            if (prop !== 'file') {
              searchParams.push("".concat(prop, "=").concat(encodeURIComponent(stringifiedData[prop])));
            }
          }
          if (searchParams.length > 0) {
            config.url += "?".concat(searchParams.join('&'));
          }
          config.file = fileConfig;
          resolve(config);
        }, function (error) {
          reject(error);
        });
      } else {
        resolve(config);
      }
    });
  }
  function isFileRequired(url) {
    return url === '/v1/api/story/upload/multi' || url === '/v2/api/talk/message/image/upload';
  }
  function getFileConfig(files) {
    var serializePromises = map(files, function (file) {
      return serializeFile(file).then(function (serialized) {
        return {
          name: file.name,
          type: file.type,
          str: serialized
        };
      });
    });
    return new es6Promise.Promise(function (resolve, reject) {
      es6Promise.Promise.all(serializePromises).then(function (serializedFiles) {
        resolve({
          paramName: 'file',
          data: serializedFiles
        });
      }, function (error) {
        reject(error);
      });
    });
  }
  function serializeFile(file) {
    return new es6Promise.Promise(function (resolve, reject) {
      if (typeof FileReader === 'undefined') {
        reject(new KakaoError('File API is not supported for this browser.'));
      }
      var fileReader = new FileReader();
      fileReader.onload = function (e) {
        try {
          resolve(ab2str(e.target.result));
        } catch (e) {
          reject(e);
        }
      };
      fileReader.onerror = function (e) {
        reject(new KakaoError("Cannot read file: ".concat(file.name)));
      };
      fileReader.readAsArrayBuffer(file);
    });
  }
  var cleanups$7 = [];
  function cleanup$8() {
    emptyCleanups(cleanups$7);
  }

  var request$6 = /*#__PURE__*/Object.freeze({
    __proto__: null,
    request: request$5,
    cleanup: cleanup$8
  });

  function getProxy(config, onResponse) {
    assignIn(config, {
      remote: URL.loginWidget,
      channel: getRandomString()
    });
    return guardCreateEasyXDM(function () {
      var proxy = new easyXDM_1.Rpc(config, {
        local: {
          postResponse: onResponse,
          getKakaoAgent: function getKakaoAgent() {
            return KAKAO_AGENT;
          }
        },
        remote: {
          getCode: {},
          getAccessToken: {},
          setClient: {},
          setStateToken: {},
          deleteAuthCookie: {}
        }
      });
      proxy.channel = config.channel;
      return proxy;
    });
  }

  var POPUP_NAME = '_blank';
  var POPUP_FEATURES$1 = 'width=380, height=520, scrollbars=yes';
  var ANDROID_WV = /Version\/4.0/i.test(UA.ua) || /; wv\)/i.test(UA.ua);
  var ANDROID_WV_ALLOWLIST = /naver\(inapp|fb_iab|daumapps|instagram|ebay/g.test(UA.ua) || (typeof daumApps === "undefined" ? "undefined" : _typeof(daumApps)) === 'object';
  function login$2(stateToken, fallbackUrl, authParams, redirectUri) {
    if (!isSupport()) {
      return;
    }
    var popup = null;
    if (UA.os.ios) {
      var iosLoginScheme = getIosLoginScheme(stateToken, authParams);
      var universalLink = "".concat(URL.universalKakaoLink).concat(encodeURIComponent(iosLoginScheme), "&web=").concat(encodeURIComponent(fallbackUrl));
      if (redirectUri) {
        location.href = universalLink;
      } else {
        popup = windowOpen(universalLink, POPUP_NAME, POPUP_FEATURES$1);
      }
    } else if (UA.os.android) {
      var androidLoginIntent = getAndroidLoginIntent(stateToken, fallbackUrl, authParams);
      if (redirectUri) {
        location.href = androidLoginIntent;
      } else if (UA.browser.version.major > 40 && (
      !ANDROID_WV || ANDROID_WV && ANDROID_WV_ALLOWLIST)) {
        popup = windowOpen(androidLoginIntent, POPUP_NAME, POPUP_FEATURES$1);
      } else {
        popup = windowOpen('', POPUP_NAME, POPUP_FEATURES$1);
        if (popup) {
          popup.addEventListener('unload', function () {
            setTimeout(function () {
              if (popup && popup.location) {
                popup.location.href = fallbackUrl;
              }
            }, 10);
          });
          popup.location.href = androidLoginIntent;
        }
      }
    }
    return popup;
  }
  function isSupport() {
    if (UA.os.ios) {
      var iOSBrowser = /safari|FxiOS|CriOS/.test(UA.ua);
      return iOSBrowser || !isTalkWebview;
    } else if (UA.os.android) {
      return UA.browser.chrome && !/opr\//i.test(UA.ua) && UA.browser.version.major >= 30 && (
      !ANDROID_WV || ANDROID_WV && ANDROID_WV_ALLOWLIST);
    }
    return false;
  }
  function getIosLoginScheme(stateToken, authParams) {
    authParams.state = stateToken;
    var params = {
      client_id: getAppKey$1(),
      redirect_uri: URL.redirectUri,
      params: JSON.stringify(authParams)
    };
    return "".concat(URL.talkLoginScheme, "?").concat(buildQueryString(params));
  }
  function getAndroidLoginIntent(stateToken, fallbackUrl, authParams) {
    return ['intent:#Intent', 'action=com.kakao.talk.intent.action.CAPRI_LOGGED_IN_ACTIVITY', 'launchFlags=0x08880000', "S.com.kakao.sdk.talk.appKey=".concat(getAppKey$1()), "S.com.kakao.sdk.talk.redirectUri=".concat(URL.talkLoginRedirectUri), "S.com.kakao.sdk.talk.state=".concat(stateToken), "S.com.kakao.sdk.talk.kaHeader=".concat(KAKAO_AGENT), "S.com.kakao.sdk.talk.extraparams=".concat(encodeURIComponent(JSON.stringify(authParams))), "S.browser_fallback_url=".concat(encodeURIComponent(fallbackUrl)), 'end;'].join(';');
  }
  var kakaotalk = {
    login: login$2,
    isSupport: isSupport
  };

  var poller$1 = new Poller(1000, 600);
  var RESERVED_REDIRECT_URI = 'kakaojs';
  function createLoginButton(settings) {
    settings = processRules(settings, rules$8.createLoginButton, 'Auth.createLoginButton');
    var container$ = getElement(settings.container);
    if (!container$) {
      throw new KakaoError('container is required for Kakao login button: pass in element or id');
    }
    var buttonSize = settings.size === 'medium' ? '02' : settings.size === 'small' ? '03' : '01';
    var buttonUrl = "".concat(URL.authDomain, "/public/widget/login/").concat(settings.lang, "/").concat(settings.lang, "_").concat(buttonSize, "_medium");
    var buttonImage = "".concat(buttonUrl, ".png");
    var hoverButtonImage = "".concat(buttonUrl, "_press.png");
    container$.innerHTML = "<img\n    id=\"kakao-login-btn\"\n    src=".concat(buttonImage, "\n    onmouseover=this.src='").concat(hoverButtonImage, "'\n    onmouseout=this.src='").concat(buttonImage, "'\n    style=\"cursor: pointer\"\n  />");
    var clickHandler = function clickHandler() {
      doLogin(settings);
    };
    addEvent(container$, 'click', clickHandler);
    cleanups$6.push(function () {
      removeEvent(container$, 'click', clickHandler);
    });
  }
  function login(settings) {
    settings = processRules(settings, rules$8.login, 'Auth.login');
    doLogin(settings);
  }
  function doLogin(settings) {
    var stateToken = getRandomString() + getRandomString();
    if (kakaotalk.isSupport() && settings.throughTalk) {
      loginThroughTalk(settings, stateToken);
    } else if (settings.redirectUri) {
      location.href = redirectLoginThroughWeb(settings);
    } else if (isNewerAndroidKakaoTalkWebView()) {
      var params = assignIn({}, makeAuthParams(settings), makeAuthExtraParams(settings), {
        redirect_uri: URL.talkLoginRedirectUri,
        response_type: 'code',
        state: stateToken,
        ka: KAKAO_AGENT,
        origin: origin
      });
      var loginUrl = makeAuthUrl(params);
      loginThroughTalk(settings, stateToken, loginUrl);
    } else {
      if (!(UA.browser.msie && parseInt(UA.browser.version.major) <= 9)) {
        addLoginEvent(settings);
      }
      var _loginUrl = loginThroughWeb(settings, stateToken);
      openLoginPopup(_loginUrl);
    }
    eventObserver.dispatch('LOGIN_START');
  }
  function addLoginEvent(settings) {
    var messageHandler = function messageHandler(_ref) {
      var origin = _ref.origin,
          data = _ref.data;
      if (/\.kakao\.com$/.test(origin) && data && typeof data === 'string') {
        var arr = data.split(' ');
        if (arr[1] === 'postResponse') {
          var resp = JSON.parse(decodeURIComponent(arr[2]));
          handleAuthResponse(settings, resp);
          removeEvent(window, 'message', messageHandler);
        }
      }
    };
    addEvent(window, 'message', messageHandler);
    cleanups$6.push(function () {
      removeEvent(window, 'message', messageHandler);
    });
  }
  function loginForm(settings) {
    settings = processRules(settings, rules$8.login, 'Auth.loginForm');
    var stateToken = getRandomString() + getRandomString();
    var reauthQueryString = '&prompt=login';
    if (settings.redirectUri) {
      location.href = "".concat(redirectLoginThroughWeb(settings)).concat(reauthQueryString);
    } else {
      var loginUrl = "".concat(loginThroughWeb(settings, stateToken)).concat(reauthQueryString);
      openLoginPopup(loginUrl);
    }
  }
  function autoLogin(settings) {
    settings = processRules(settings, rules$8.autoLogin, 'Auth.autoLogin');
    if (isIOSKakaoTalkWebView() || isAndroidKakaoTalkWebView()) {
      var stateToken = getRandomString() + getRandomString();
      var params = assignIn({}, makeAuthParams(settings), {
        redirect_uri: URL.talkLoginRedirectUri,
        response_type: 'code',
        state: stateToken,
        ka: KAKAO_AGENT,
        origin: origin,
        prompt: 'none'
      });
      var loginUrl = makeAuthUrl(params);
      loginThroughTalk(settings, stateToken, loginUrl);
    } else {
      runAuthCallback(settings, {
        error: 'auto_login',
        error_description: 'Kakao.Auth.autoLogin is only supported by KakaoTalk InAppBrowser',
        error_code: '400',
        status: 'error'
      });
    }
    eventObserver.dispatch('LOGIN_START');
  }
  var popupForTalk = null;
  var closePopup = function closePopup() {
    popupForTalk && popupForTalk.close && popupForTalk.close();
    popupForTalk = null;
  };
  var proxyForTalk = null;
  var prevCode = null;
  function loginThroughTalk(settings, stateToken, talkLoginUrl) {
    if (!proxyForTalk) {
      proxyForTalk = getProxy({}, function (response) {
        if (response.status === 'error' && response.error_code && response.error_code !== '300') {
          poller$1.stop();
          if (response.error_code === '700') {
            location.href = "".concat(URL.authDomain, "/error/network");
          }
          handleAuthResponse(settings, {
            error: response.error,
            error_description: response.error_description
          });
        }
        if (response.status) {
          if (response.status === 'ok') {
            poller$1.stop();
            if (prevCode === response.code) {
              return;
            } else {
              prevCode = response.code;
            }
            proxyForTalk.getAccessToken(response.code, getAppKey$1(), UA.os.ios && !talkLoginUrl ? URL.redirectUri : URL.talkLoginRedirectUri, settings.approvalType);
            closePopup();
          } else {
            if (UA.os.ios && popupForTalk.location.href === 'about:blank') {
              closePopup();
            }
          }
        } else {
          handleAuthResponse(settings, response);
        }
      });
      cleanups$6.push(function () {
        proxyForTalk.destroy();
        proxyForTalk = null;
      });
    }
    var fallbackUrl = '';
    if (talkLoginUrl) {
      if (settings.redirectUri) {
        location.href = talkLoginUrl;
      } else {
        openLoginPopup(talkLoginUrl);
      }
    } else {
      fallbackUrl = settings.redirectUri ? redirectLoginThroughWeb(settings) : loginThroughWeb(settings, stateToken, UA.os.ios ? URL.redirectUri : URL.talkLoginRedirectUri);
      var params = assignIn({}, makeAuthParams(settings), makeAuthExtraParams(settings));
      setTimeout(function () {
        popupForTalk = kakaotalk.login(stateToken, fallbackUrl, params, settings.redirectUri);
      }, 500);
    }
    poller$1.start(function () {
      if (stateToken) {
        proxyForTalk.getCode(stateToken, getAppKey$1(), KAKAO_AGENT);
      }
    }, function () {
      handleAuthResponse(settings, {
        error: 'timeout',
        description: 'Account login timed out. Please login again.',
        error_description: 'Account login timed out. Please login again.'
      });
      if (settings.redirectUri) {
        location.href = fallbackUrl;
      } else {
        openLoginPopup(fallbackUrl);
      }
    });
  }
  var proxyForWeb = null;
  var savedSettingsForWeb = {};
  function loginThroughWeb(settings, stateToken, fallbackUrl) {
    if (!proxyForWeb) {
      proxyForWeb = getProxy({}, function (response) {
        var savedSettings = getSavedSettingsWithResponseState(response, savedSettingsForWeb);
        handleAuthResponse(savedSettings, response);
      });
      cleanups$6.push(function () {
        proxyForWeb.destroy();
        proxyForWeb = null;
      });
    }
    savedSettingsForWeb[stateToken] = settings;
    var redirectUri = settings.redirectUri ? settings.redirectUri : fallbackUrl ? fallbackUrl : RESERVED_REDIRECT_URI;
    var params = assignIn({}, makeAuthParams(settings), makeAuthExtraParams(settings), {
      redirect_uri: redirectUri,
      response_type: 'code',
      state: stateToken,
      proxy: "easyXDM_Kakao_".concat(proxyForWeb.channel, "_provider"),
      ka: KAKAO_AGENT,
      origin: origin
    });
    return makeAuthUrl(params);
  }
  function redirectLoginThroughWeb(settings) {
    var params = assignIn({}, makeAuthParams(settings), makeAuthExtraParams(settings), {
      redirect_uri: settings.redirectUri,
      response_type: 'code',
      ka: KAKAO_AGENT,
      origin: origin
    });
    return makeAuthUrl(params);
  }
  function getSavedSettingsWithResponseState(response, settings) {
    if (!settings[response.stateToken]) {
      throw new KakaoError('security error: #CST2');
    }
    var savedSettings = settings[response.stateToken];
    delete settings[response.stateToken];
    delete response.stateToken;
    return savedSettings;
  }
  function handleAuthResponse(settings, resp) {
    if (resp.error) {
      if (resp.error !== 'access_denied') {
        setAccessToken(null);
      }
    } else {
      setAccessToken(resp.access_token, settings.persistAccessToken);
      eventObserver.dispatch('LOGIN');
    }
    runAuthCallback(settings, resp);
  }
  function logout() {
    var callback = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : emptyFunc;
    validate(callback, isFunction, 'Auth.logout');
    request$5({
      url: '/v1/user/logout',
      always: function always() {
        setAccessToken(null);
        eventObserver.dispatch('LOGOUT');
        callback(true);
      }
    });
  }
  var proxyForAccessToken = null;
  function issueAccessToken(settings) {
    settings = processRules(settings, rules$8.issueAccessToken, 'Auth.issueAccessToken');
    if (!proxyForAccessToken) {
      proxyForAccessToken = getProxy({}, function (response) {
        handleAuthResponse(settings, response);
      });
      cleanups$6.push(function () {
        proxyForAccessToken.destroy();
        proxyForAccessToken = null;
      });
    }
    proxyForAccessToken.getAccessToken(settings.code, getAppKey$1(), settings.redirectUri);
  }
  var cleanups$6 = [];
  function cleanup$7() {
    emptyCleanups(cleanups$6);
  }

  var login$1 = /*#__PURE__*/Object.freeze({
    __proto__: null,
    createLoginButton: createLoginButton,
    login: login,
    loginForm: loginForm,
    autoLogin: autoLogin,
    logout: logout,
    issueAccessToken: issueAccessToken,
    cleanup: cleanup$7
  });

  function getStatusInfo(callback) {
    validate(callback, isFunction, 'Auth.getStatusInfo');
    if (!getAccessToken()) {
      callback({
        status: 'not_connected'
      });
    } else {
      request$5({
        url: '/v2/user/me',
        success: function success(res) {
          callback({
            status: 'connected',
            user: res
          });
        },
        fail: function fail() {
          callback({
            status: 'not_connected'
          });
        }
      });
    }
  }

  var status = /*#__PURE__*/Object.freeze({
    __proto__: null,
    getStatusInfo: getStatusInfo
  });

  function selectShippingAddress(settings) {
    settings = processRules(settings, rules$8.selectShippingAddress, 'Auth.selectShippingAddress');
    requestShippingAddress(settings, '/user/address');
  }
  function updateShippingAddress(settings) {
    settings = processRules(settings, rules$8.updateShippingAddress, 'Auth.updateShippingAddress');
    requestShippingAddress(settings, '/user/edit/address');
  }
  function requestShippingAddress(settings, subpath) {
    cleanup$6();
    var transId = generateTxId();
    var params = _objectSpread2({
      app_key: getAppKey$1(),
      access_token: getAccessToken(),
      ka: KAKAO_AGENT,
      trans_id: transId
    }, settings.addressId && {
      address_id: settings.addressId
    });
    var url = URL.appsDomain + subpath;
    if (settings.returnUrl) {
      params.return_url = settings.returnUrl;
      createAndSubmitForm(params, url);
    } else {
      createHiddenIframe(transId, "".concat(URL.appsDomain, "/proxy?trans_id=").concat(transId), cleanups$5);
      addMessageEvent(settings, URL.appsDomain, cleanups$5);
      openPopupAndSubmitForm(params, {
        url: url,
        popupName: 'shipping_address',
        popupFeatures: "location=no,resizable=no,status=no,scrollbars=no,width=460,height=608"
      });
    }
  }
  var cleanups$5 = [];
  function cleanup$6() {
    emptyCleanups(cleanups$5);
  }

  var shippingAddress = /*#__PURE__*/Object.freeze({
    __proto__: null,
    selectShippingAddress: selectShippingAddress,
    updateShippingAddress: updateShippingAddress,
    cleanup: cleanup$6
  });

  var Auth = makeModule([oauth, login$1, secret, status, shippingAddress]);

  var API = makeModule([request$6]);

  function capitalize(str) {
    return str.charAt(0).toUpperCase() + str.slice(1);
  }
  function camelToSnakeCase(str) {
    return str.replace(/[A-Z]/g, function (letter) {
      return "_".concat(letter.toLowerCase());
    });
  }
  function stringifyLCBA(lcba) {
    return isObject(lcba) ? JSON.stringify(lcba) : lcba;
  }
  function requestAPI(url, data) {
    return request$5({
      url: url,
      data: data
    });
  }

  function partialValidator(settings, rule, propName) {
    processRules(settings, rule, "parameter \"".concat(propName, "\" in Link"));
    return true;
  }
  function formatter(settings) {
    return keys(settings).reduce(function (obj, k) {
      obj[camelToSnakeCase(k)] = settings[k];
      return obj;
    }, {});
  }
  var linkRule = {
    optional: {
      webUrl: isString,
      mobileWebUrl: isString,
      androidExecutionParams: isString,
      androidExecParams: isString,
      iosExecutionParams: isString,
      iosExecParams: isString
    },
    builder: makeLink
  };
  var itemRule = {
    required: {
      item: isString,
      itemOp: isString
    }
  };
  function makeLink(settings) {
    var link = formatter(settings);
    if (link.android_exec_params) {
      link.android_execution_params = link.android_exec_params;
      delete link.android_exec_params;
    }
    if (link.ios_exec_params) {
      link.ios_execution_params = link.ios_exec_params;
      delete link.ios_exec_params;
    }
    return link;
  }
  function makeButton(settings) {
    return {
      title: settings.title,
      link: makeLink(settings.link)
    };
  }
  function makeContent(settings) {
    var content = formatter(settings);
    content.link = makeLink(content.link);
    return content;
  }
  function makeItemContent(settings) {
    var itemContent = formatter(settings);
    if (itemContent.items) {
      itemContent.items = map(itemContent.items, function (e) {
        return formatter(e);
      });
    }
    return itemContent;
  }
  var rules$6 = {
    headerLink: linkRule,
    link: linkRule,
    button: {
      required: {
        title: isString,
        link: function link(e) {
          partialValidator(e, linkRule, 'link');
        }
      },
      builder: makeButton
    },
    buttons: {
      optional: {
        0: function _(e) {
          partialValidator(e, rules$6.button, 'button');
        },
        1: function _(e) {
          partialValidator(e, rules$6.button, 'button');
        }
      },
      builder: function builder(arr) {
        return map(arr, makeButton);
      }
    },
    content: {
      required: {
        title: isString,
        imageUrl: isString,
        link: function link(e) {
          partialValidator(e, linkRule, 'link');
        }
      },
      optional: {
        imageWidth: isInteger,
        imageHeight: isInteger,
        description: isString
      },
      builder: makeContent
    },
    contents: {
      optional: {
        0: function _(e) {
          partialValidator(e, rules$6.content, 'content');
        },
        1: function _(e) {
          partialValidator(e, rules$6.content, 'content');
        },
        2: function _(e) {
          partialValidator(e, rules$6.content, 'content');
        }
      },
      builder: function builder(arr) {
        return map(arr, makeContent);
      }
    },
    commerce: {
      required: {
        regularPrice: isInteger
      },
      optional: {
        discountPrice: isInteger,
        discountRate: isInteger,
        fixedDiscountPrice: isInteger,
        currencyUnit: isString,
        currencyUnitPosition: isOneOf([0, 1]),
        productName: isString
      },
      builder: formatter
    },
    social: {
      optional: {
        likeCount: isInteger,
        commentCount: isInteger,
        sharedCount: isInteger,
        viewCount: isInteger,
        subscriberCount: isInteger
      },
      builder: formatter
    },
    itemContent: {
      optional: {
        profileText: isString,
        profileImageUrl: isString,
        titleImageUrl: isString,
        titleImageText: isString,
        titleImageCategory: isString,
        items: function items(arr) {
          return isArray(arr) && arr.length < 6 && every(arr, function (e) {
            return partialValidator(e, itemRule, 'items.item');
          });
        },
        sum: isString,
        sumOp: isString
      },
      builder: makeItemContent
    }
  };
  function create(settings, key, callerMsg) {
    var linkPropRule = rules$6[key];
    if (linkPropRule) {
      settings = processRules(settings, linkPropRule, "parameter \"".concat(key, "\" in ").concat(callerMsg || 'Link'));
      return linkPropRule.builder(settings);
    }
  }
  var propGenerator = {
    create: create
  };

  var LINK_VER = '4.0';
  var KakaoLink = function KakaoLink(settings, validatedResp) {
    _classCallCheck(this, KakaoLink);
    this.appkey = getAppKey$1();
    this.appver = '1.0';
    this.linkver = LINK_VER;
    this.extras = _objectSpread2(_objectSpread2({
      KA: KAKAO_AGENT
    }, settings.extras), settings.serverCallbackArgs && {
      lcba: stringifyLCBA(settings.serverCallbackArgs)
    });
    this.template_json = validatedResp.template_msg;
    this.template_args = validatedResp.template_args;
    this.template_id = validatedResp.template_id;
  };
  function makeKakaoLink(settings, validatedResp) {
    var kakaoLink = new KakaoLink(settings, validatedResp);
    if (JSON.stringify(kakaoLink).length > 10000) {
      throw new KakaoError('Failed to send message because it exceeds the message size limit. Please contact the app administrator.');
    }
    var linkScheme = UA.os.ios ? URL.talkLinkScheme : 'kakaolink://send';
    return "".concat(linkScheme, "?").concat(buildQueryString(kakaoLink));
  }
  var DefaultLink = function DefaultLink(settings) {
    var _this = this;
    _classCallCheck(this, DefaultLink);
    this.link_ver = LINK_VER;
    this.template_object = _objectSpread2({
      object_type: settings.objectType
    }, settings.buttonTitle && {
      button_title: settings.buttonTitle
    });
    forEach(settings, function (setting, key) {
      var prop = propGenerator.create(setting, key, 'defaultObject');
      if (prop) {
        _this.template_object[camelToSnakeCase(key)] = prop;
      }
    });
  };
  var ListLink = function (_DefaultLink) {
    _inherits(ListLink, _DefaultLink);
    var _super = _createSuper(ListLink);
    function ListLink(settings) {
      var _this2;
      _classCallCheck(this, ListLink);
      _this2 = _super.call(this, settings);
      _this2.template_object.header_title = settings.headerTitle;
      if (console && (settings.headerImageUrl || settings.headerImageWidth || settings.headerImageHeight)) {
        console.warn("KakaoWarning: The parameters (".concat(['headerImageUrl', 'headerImageWidth', 'headerImageHeight'].join(', '), ") for header background image are deprecated."));
      }
      return _this2;
    }
    return ListLink;
  }(DefaultLink);
  var LocationLink = function (_DefaultLink2) {
    _inherits(LocationLink, _DefaultLink2);
    var _super2 = _createSuper(LocationLink);
    function LocationLink(settings) {
      var _this3;
      _classCallCheck(this, LocationLink);
      _this3 = _super2.call(this, settings);
      var tpl = _this3.template_object;
      tpl.address = settings.address || '';
      tpl.address_title = settings.addressTitle || '';
      return _this3;
    }
    return LocationLink;
  }(DefaultLink);
  var TextLink = function (_DefaultLink3) {
    _inherits(TextLink, _DefaultLink3);
    var _super3 = _createSuper(TextLink);
    function TextLink(settings) {
      var _this4;
      _classCallCheck(this, TextLink);
      _this4 = _super3.call(this, settings);
      _this4.template_object.text = settings.text || '';
      return _this4;
    }
    return TextLink;
  }(DefaultLink);
  var defaultLinks = {
    FeedLink: DefaultLink,
    CommerceLink: DefaultLink,
    ListLink: ListLink,
    LocationLink: LocationLink,
    TextLink: TextLink
  };
  var ScrapLink = function ScrapLink(settings) {
    _classCallCheck(this, ScrapLink);
    this.link_ver = LINK_VER;
    this.request_url = settings.requestUrl;
    if (settings.templateId) {
      this.template_id = settings.templateId;
    }
    if (settings.templateArgs) {
      this.template_args = settings.templateArgs;
    }
  };
  var CustomLink = function CustomLink(settings) {
    _classCallCheck(this, CustomLink);
    this.link_ver = LINK_VER;
    this.template_id = settings.templateId;
    this.template_args = settings.templateArgs;
  };
  function makeDefaultLink(settings) {
    var clazz = defaultLinks["".concat(capitalize(settings.objectType), "Link")];
    return new clazz(settings);
  }
  function makeScrapLink(settings) {
    return new ScrapLink(settings);
  }
  function makeCustomLink(settings) {
    return new CustomLink(settings);
  }

  var LINK_POPUP_NAME = 'kakao_link_web_sharer';
  var LINK_POPUP_FEATURES = 'location=no,resizable=no,status=no,scrollbars=no,width=460,height=608';
  var LINK_URL_LIMIT = 2084;
  function send$1(settings, linkType, linkObj) {
    var webLinkParams = {
      app_key: getAppKey$1(),
      ka: KAKAO_AGENT,
      validation_action: linkType,
      validation_params: JSON.stringify(linkObj)
    };
    if (settings.serverCallbackArgs) {
      webLinkParams.lcba = stringifyLCBA(settings.serverCallbackArgs);
    }
    var webLinkUrl = "".concat(URL.sharerDomain, "/talk/friends/picker/easylink?").concat(buildQueryString(webLinkParams));
    var linkPopup = null;
    if (!(UA.browser.msie || UA.browser.spartan) && webLinkUrl.length < LINK_URL_LIMIT) {
      linkPopup = windowOpen(webLinkUrl, LINK_POPUP_NAME, LINK_POPUP_FEATURES);
      linkPopup.focus();
    } else {
      var popupParams = {
        url: "".concat(URL.sharerDomain, "/talk/friends/picker/link"),
        popupName: LINK_POPUP_NAME,
        popupFeatures: LINK_POPUP_FEATURES
      };
      linkPopup = openPopupAndSubmitForm(webLinkParams, popupParams);
    }
    if (settings.callback) {
      handleCallback(linkPopup, settings.callback);
    }
  }
  function handleCallback(popup, callback) {
    if (UA.browser.msie) {
      if (console) {
        console.warn('KakaoWarning: The callback parameter does not support the IE browser.');
      }
      return;
    }
    var linkCallback = function linkCallback(e) {
      if (e.data === 'sent' && e.origin === URL.sharerDomain) {
        callback();
      }
    };
    addEvent(window, 'message', linkCallback);
    var interval = setInterval(function () {
      if (popup.closed) {
        clearInterval(interval);
        removeEvent(window, 'message', linkCallback);
      }
    }, 1000);
  }
  var webSender = {
    send: send$1
  };

  var web2app = function () {
    var ua_parser$1 = ua_parser;
    var TIMEOUT_IOS = 5 * 1000,
        TIMEOUT_ANDROID = 3 * 100,
        INTERVAL = 100,
        ua = ua_parser$1(),
        os = ua.os,
        intentNotSupportedBrowserList = ['firefox', 'opr/'],
        intentSupportCustomBrowserList = ['KAKAOTALK'
    ];
    function moveToStore(storeURL) {
      window.top.location.replace(storeURL);
    }
    function web2app(context) {
      var willInvokeApp = typeof context.willInvokeApp === 'function' ? context.willInvokeApp : function () {},
          onAppMissing = typeof context.onAppMissing === 'function' ? context.onAppMissing : moveToStore,
          onUnsupportedEnvironment = typeof context.onUnsupportedEnvironment === 'function' ? context.onUnsupportedEnvironment : function () {};
      willInvokeApp();
      if (os.android) {
        if (isIntentSupportedBrowser() && context.intentURI && !context.useUrlScheme) {
          web2appViaIntentURI(context.intentURI);
        } else if (context.storeURL) {
          web2appViaCustomUrlSchemeForAndroid(context.urlScheme, context.storeURL, onAppMissing);
        }
      } else if (os.ios && context.storeURL) {
        web2appViaCustomUrlSchemeForIOS(context.urlScheme, context.storeURL, onAppMissing, context.universalLink);
      } else {
        setTimeout(function () {
          onUnsupportedEnvironment();
        }, 100);
      }
    }
    function isIntentSupportedBrowser() {
      var supportsIntent = ua.browser.chrome && +ua.browser.version.major >= 25;
      var blackListRegexp = new RegExp(intentNotSupportedBrowserList.join('|'), "i");
      var whiteListRegexp = new RegExp(intentSupportCustomBrowserList.join('|'), "i");
      return supportsIntent && !blackListRegexp.test(ua.ua) || whiteListRegexp.test(ua.ua);
    }
    function web2appViaCustomUrlSchemeForAndroid(urlScheme, storeURL, fallback) {
      deferFallback(TIMEOUT_ANDROID, storeURL, fallback);
      launchAppViaHiddenIframe(urlScheme);
    }
    function deferFallback(timeout, storeURL, fallback) {
      var clickedAt = new Date().getTime();
      return setTimeout(function () {
        var now = new Date().getTime();
        if (isPageVisible() && now - clickedAt < timeout + INTERVAL) {
          fallback(storeURL);
        }
      }, timeout);
    }
    function web2appViaIntentURI(launchURI) {
      if (ua.browser.chrome) {
        move();
      } else {
        setTimeout(move, 100);
      }
      function move() {
        top.location.href = launchURI;
      }
    }
    function web2appViaCustomUrlSchemeForIOS(urlScheme, storeURL, fallback, universalLink) {
      var tid = deferFallback(TIMEOUT_IOS, storeURL, fallback);
      if (parseInt(ua.os.version.major, 10) < 8) {
        bindPagehideEvent(tid);
      } else {
        bindVisibilityChangeEvent(tid);
      }
      if (isSupportUniversalLinks()) {
        if (universalLink === undefined) {
          universalLink = urlScheme;
        } else {
          clearTimeout(tid);
        }
        launchAppViaChangingLocation(universalLink);
      } else {
        launchAppViaHiddenIframe(urlScheme);
      }
    }
    function bindPagehideEvent(tid) {
      window.addEventListener('pagehide', function clear() {
        if (isPageVisible()) {
          clearTimeout(tid);
          window.removeEventListener('pagehide', clear);
        }
      });
    }
    function bindVisibilityChangeEvent(tid) {
      document.addEventListener('visibilitychange', function clear() {
        if (isPageVisible()) {
          clearTimeout(tid);
          document.removeEventListener('visibilitychange', clear);
        }
      });
    }
    function isPageVisible() {
      var attrNames = ['hidden', 'webkitHidden'];
      for (var i = 0, len = attrNames.length; i < len; i++) {
        if (typeof document[attrNames[i]] !== 'undefined') {
          return !document[attrNames[i]];
        }
      }
      return true;
    }
    function launchAppViaChangingLocation(urlScheme) {
      window.top.location.replace(urlScheme);
    }
    function launchAppViaHiddenIframe(urlScheme) {
      setTimeout(function () {
        var iframe = createHiddenIframe('appLauncher');
        iframe.src = urlScheme;
      }, 100);
    }
    function createHiddenIframe(id) {
      var iframe = document.createElement('iframe');
      iframe.id = id;
      iframe.style.border = 'none';
      iframe.style.width = '0';
      iframe.style.height = '0';
      iframe.style.display = 'none';
      iframe.style.overflow = 'hidden';
      document.body.appendChild(iframe);
      return iframe;
    }
    function isSupportUniversalLinks() {
      return parseInt(ua.os.version.major, 10) > 8 && ua.os.ios;
    }
    return web2app;
  }();

  var poller = new Poller(100, 100);
  var KAKAOTALK_IOS_APP_ID = '362057947';
  function send(settings, requestUrl, linkObj) {
    var onResponse = null;
    if (UA.browser.iphone && /version/.test(UA.ua.toLowerCase())) {
      var response = null;
      onResponse = function onResponse(res) {
        response = res;
      };
      poller.start(function () {
        if (response !== null) {
          poller.stop();
          handleValidatedResp(response, settings);
        }
      }, function () {
        var error = {
          error: 'timeout',
          error_description: 'LINK_TIMEOUT'
        };
        settings.fail(error);
        settings.always(error);
      });
    } else {
      onResponse = handleValidatedResp;
    }
    return requestAPI(requestUrl, linkObj).then(function (validatedResp) {
      onResponse(validatedResp, settings);
    }, function (error) {
      settings.fail(error);
      settings.always(error);
    });
  }
  function handleValidatedResp(validatedResp, settings) {
    var linkScheme = makeKakaoLink(settings, validatedResp);
    callWeb2app$1(linkScheme, settings.fail, settings.installTalk);
    var msg = {
      template_msg: validatedResp.template_msg || {},
      warning_msg: validatedResp.warning_msg || {},
      argument_msg: validatedResp.argument_msg || {}
    };
    settings.success(msg);
    settings.always(msg);
  }
  function callWeb2app$1(linkScheme, unsupportedCallback, shouldInstallTalk) {
    var androidIntent = ["intent:".concat(linkScheme, "#Intent"), 'launchFlags=0x14008000', "".concat(shouldInstallTalk === true ? "package=".concat(URL.talkAndroidPackage, ";") : '', "end;")].join(';');
    var web2appOptions = {
      urlScheme: linkScheme,
      intentURI: androidIntent,
      appName: 'KakaoTalk',
      storeURL: getInstallUrl(URL.talkAndroidPackage, KAKAOTALK_IOS_APP_ID),
      onUnsupportedEnvironment: function onUnsupportedEnvironment() {
        unsupportedCallback(linkScheme);
      }
    };
    if (!shouldInstallTalk || isIOSKakaoTalkWebView() || isAndroidWebView()) {
      web2appOptions.onAppMissing = emptyFunc;
    }
    if (isIOSKakaoTalkWebView()) {
      web2appOptions.universalLink = undefined;
    }
    try {
      web2app(web2appOptions);
    } catch (error) {
    }
  }
  var talkSender = {
    send: send
  };

  var commonLinkOptional = {
    success: isFunction,
    fail: isFunction,
    always: isFunction,
    callback: isFunction,
    installTalk: isBoolean,
    throughTalk: isBoolean,
    extras: isObject,
    serverCallbackArgs: passesOneOf([isJSONString, isObject])
  };
  var commonLinkDefaults = {
    success: emptyFunc,
    fail: emptyFunc,
    always: emptyFunc,
    installTalk: false,
    throughTalk: true
  };
  function buttonsValidator(e) {
    if (!isArray(e)) {
      return false;
    } else if (e.length > 2) {
      throw new KakaoError('Illegal argument for "buttons" in Link: size of buttons should be up to 2');
    }
    return true;
  }
  var sendFeed = {
    required: {
      objectType: function objectType(type) {
        return type === 'feed';
      },
      content: isObject
    },
    optional: assignIn({
      itemContent: isObject,
      social: isObject,
      buttonTitle: isString,
      buttons: buttonsValidator
    }, commonLinkOptional),
    defaults: commonLinkDefaults
  };
  var sendList = {
    required: {
      objectType: function objectType(type) {
        return type === 'list';
      },
      headerTitle: isString,
      headerLink: isObject,
      contents: function contents(e) {
        if (!isArray(e)) {
          return false;
        } else if (e.length < 2 || e.length > 3) {
          throw new KakaoError('Illegal argument for "contents" in Link: size of contents should be more than 1 and up to 3');
        }
        return true;
      }
    },
    optional: assignIn({
      buttonTitle: isString,
      buttons: buttonsValidator,
      headerImageUrl: isString,
      headerImageWidth: isInteger,
      headerImageHeight: isInteger
    }, commonLinkOptional),
    defaults: commonLinkDefaults
  };
  var sendCommerce = {
    required: {
      objectType: function objectType(type) {
        return type === 'commerce';
      },
      content: isObject,
      commerce: isObject
    },
    optional: assignIn({
      buttonTitle: isString,
      buttons: buttonsValidator
    }, commonLinkOptional),
    defaults: commonLinkDefaults
  };
  var sendLocation = {
    required: {
      objectType: function objectType(type) {
        return type === 'location';
      },
      content: isObject,
      address: isString
    },
    optional: assignIn({
      addressTitle: isString,
      social: isObject,
      buttonTitle: isString,
      buttons: buttonsValidator
    }, commonLinkOptional),
    defaults: commonLinkDefaults
  };
  var sendText = {
    required: {
      objectType: function objectType(type) {
        return type === 'text';
      },
      text: isString,
      link: isObject
    },
    optional: assignIn({
      buttonTitle: isString,
      buttons: buttonsValidator
    }, commonLinkOptional),
    defaults: commonLinkDefaults
  };
  var sendScrap$1 = {
    required: {
      requestUrl: isString
    },
    optional: assignIn({
      templateId: isInteger,
      templateArgs: isObject
    }, commonLinkOptional),
    defaults: assignIn({
      templateArgs: {}
    }, commonLinkDefaults)
  };
  var sendCustom$1 = {
    required: {
      templateId: isInteger
    },
    optional: assignIn({
      templateArgs: isObject
    }, commonLinkOptional),
    defaults: assignIn({
      templateArgs: {}
    }, commonLinkDefaults)
  };
  function extendRuleForContainer(rule) {
    return defaults({
      required: assignIn({
        container: passesOneOf([isElement, isString])
      }, rule.required)
    }, rule);
  }
  var rules$5 = {
    defaultObjectTypes: ['feed', 'list', 'commerce', 'location', 'text'],
    sendFeed: sendFeed,
    createFeedButton: extendRuleForContainer(sendFeed),
    sendList: sendList,
    createListButton: extendRuleForContainer(sendList),
    sendCommerce: sendCommerce,
    createCommerceButton: extendRuleForContainer(sendCommerce),
    sendLocation: sendLocation,
    createLocationButton: extendRuleForContainer(sendLocation),
    sendText: sendText,
    createTextButton: extendRuleForContainer(sendText),
    sendScrap: sendScrap$1,
    createScrapButton: extendRuleForContainer(sendScrap$1),
    sendCustom: sendCustom$1,
    createCustomButton: extendRuleForContainer(sendCustom$1),
    uploadImage: {
      required: {
        file: isObject
      }
    },
    deleteImage: {
      required: {
        imageUrl: isString
      }
    },
    scrapImage: {
      required: {
        imageUrl: isString
      }
    }
  };

  function createDefaultButton(settings) {
    if (!settings.objectType || !isOneOf(rules$5.defaultObjectTypes)(settings.objectType)) {
      throw new KakaoError("objectType should be one of (".concat(rules$5.defaultObjectTypes.join(', '), ")"));
    }
    var rule = rules$5["create".concat(capitalize(settings.objectType), "Button")];
    settings = processRules(settings, rule, 'Link.createDefaultButton');
    addClickEvent(settings, 'default');
  }
  function sendDefault(settings) {
    if (!settings.objectType || !isOneOf(rules$5.defaultObjectTypes)(settings.objectType)) {
      throw new KakaoError("objectType should be one of (".concat(rules$5.defaultObjectTypes.join(', '), ")"));
    }
    var rule = rules$5["send".concat(capitalize(settings.objectType))];
    settings = processRules(settings, rule, 'Link.sendDefault');
    doSend(settings, 'default');
  }
  function createScrapButton(settings) {
    settings = processRules(settings, rules$5.createScrapButton, 'Link.createScrapButton');
    addClickEvent(settings, 'scrap');
  }
  function sendScrap(settings) {
    settings = processRules(settings, rules$5.sendScrap, 'Link.sendScrap');
    doSend(settings, 'scrap');
  }
  function createCustomButton(settings) {
    settings = processRules(settings, rules$5.createCustomButton, 'Link.createCustomButton');
    addClickEvent(settings, 'custom');
  }
  function sendCustom(settings) {
    settings = processRules(settings, rules$5.sendCustom, 'Link.sendCustom');
    doSend(settings, 'custom');
  }
  function addClickEvent(settings, linkType) {
    var container$ = getElement(settings.container);
    if (!container$) {
      throw new KakaoError('container is required for KakaoTalk Link: pass in element or id');
    }
    var clickHandler = function clickHandler(e) {
      e.preventDefault();
      e.stopPropagation();
      doSend(settings, linkType);
    };
    addEvent(container$, 'click', clickHandler);
    cleanups$4.push(function () {
      removeEvent(container$, 'click', clickHandler);
    });
  }
  var linkTypeMapper = {
    "default": {
      makeLinkFunc: makeDefaultLink,
      requestUrl: '/v2/api/kakaolink/talk/template/default'
    },
    scrap: {
      makeLinkFunc: makeScrapLink,
      requestUrl: '/v2/api/kakaolink/talk/template/scrap'
    },
    custom: {
      makeLinkFunc: makeCustomLink,
      requestUrl: '/v2/api/kakaolink/talk/template/validate'
    }
  };
  function doSend(settings, linkType) {
    var _linkTypeMapper$linkT = linkTypeMapper[linkType],
        makeLinkFunc = _linkTypeMapper$linkT.makeLinkFunc,
        requestUrl = _linkTypeMapper$linkT.requestUrl;
    var linkObj = makeLinkFunc(settings);
    var isIpad = UA.os.ios && UA.platform === 'tablet';
    if (isTalkWebview || settings.throughTalk && (UA.platform === 'mobile' || isIpad)) {
      talkSender.send(settings, requestUrl, linkObj);
    } else {
      webSender.send(settings, linkType, linkObj);
    }
  }
  var cleanups$4 = [];
  function cleanup$5() {
    emptyCleanups(cleanups$4);
  }

  var linker = /*#__PURE__*/Object.freeze({
    __proto__: null,
    createDefaultButton: createDefaultButton,
    sendDefault: sendDefault,
    createScrapButton: createScrapButton,
    sendScrap: sendScrap,
    createCustomButton: createCustomButton,
    sendCustom: sendCustom,
    cleanup: cleanup$5
  });

  function uploadImage(settings) {
    settings = processRules(settings, rules$5.uploadImage, 'Link.uploadImage');
    return requestAPI('/v2/api/talk/message/image/upload', {
      file: settings.file
    });
  }
  function deleteImage(settings) {
    settings = processRules(settings, rules$5.deleteImage, 'Link.deleteImage');
    return requestAPI('/v2/api/talk/message/image/delete', {
      image_url: settings.imageUrl
    });
  }
  function scrapImage(settings) {
    settings = processRules(settings, rules$5.scrapImage, 'Link.scrapImage');
    return requestAPI('/v2/api/talk/message/image/scrap', {
      image_url: settings.imageUrl
    });
  }

  var imageAPI = /*#__PURE__*/Object.freeze({
    __proto__: null,
    uploadImage: uploadImage,
    deleteImage: deleteImage,
    scrapImage: scrapImage
  });

  var Link = makeModule([linker, imageAPI]);

  var POPUP_FEATURES = 'width=350, height=510';
  function createAnchorImage$1(settings, imgSrc, imgTitle) {
    var a$ = document.createElement('a');
    a$.setAttribute('href', '#');
    var img$ = document.createElement('img');
    img$.setAttribute('src', imgSrc);
    img$.setAttribute('title', imgTitle);
    img$.setAttribute('alt', imgTitle);
    if (settings.supportMultipleDensities) {
      img$.setAttribute('srcset', [imgSrc.replace('.png', '_2X.png 2x'), imgSrc.replace('.png', '_3X.png 3x')].join(', '));
    }
    a$.appendChild(img$);
    return a$;
  }
  function makeChannelParams(apiVer) {
    return buildQueryString({
      api_ver: apiVer,
      kakao_agent: KAKAO_AGENT,
      app_key: getAppKey$1(),
      referer: origin + location.pathname + location.search
    });
  }

  var sizes = ['small', 'large'];
  var colors = ['yellow', 'mono'];
  var shapes = ['pc', 'mobile'];
  var titles = ['consult', 'question'];
  var rules$4 = {
    createAddChannelButton: {
      required: {
        container: passesOneOf([isElement, isString]),
        channelPublicId: isString
      },
      optional: {
        size: isOneOf(sizes),
        supportMultipleDensities: isBoolean
      },
      defaults: {
        size: sizes[0],
        supportMultipleDensities: false
      }
    },
    addChannel: {
      required: {
        channelPublicId: isString
      }
    },
    createChatButton: {
      required: {
        container: passesOneOf([isElement, isString]),
        channelPublicId: isString
      },
      optional: {
        size: isOneOf(sizes),
        color: isOneOf(colors),
        shape: isOneOf(shapes),
        title: isOneOf(titles),
        supportMultipleDensities: isBoolean
      },
      defaults: {
        size: sizes[0],
        color: colors[0],
        shape: shapes[0],
        title: titles[0],
        supportMultipleDensities: false
      }
    },
    chat: {
      required: {
        channelPublicId: isString
      }
    }
  };

  var API_VER$1 = '1.1';
  var ADD_CHANNEL_POPUP_NAME = 'channel_add_social_plugin';
  var CHAT_POPUP_NAME$1 = 'channel_chat_social_plugin';
  function createAddChannelButton(settings) {
    var container$ = getElement(settings.container);
    if (!container$) {
      throw new KakaoError('container is required for Channel.createAddChannelButton: pass in element or id');
    } else {
      applyAttributes(settings, container$, {
        channelPublicId: 'data-channel-public-id',
        size: 'data-size',
        supportMultipleDensities: 'data-support-multiple-densities'
      });
    }
    settings = processRules(settings, rules$4.createAddChannelButton, 'Channel.createAddChannelButton');
    var imgSrc = getAddChannelImgSrc(settings);
    var anchor$ = createAnchorImage$1(settings, imgSrc, '카카오톡 채널 추가 버튼');
    container$.appendChild(anchor$);
    var clickHandler = function clickHandler(e) {
      e.preventDefault();
      openAddChannelPopup(settings);
    };
    addEvent(anchor$, 'click', clickHandler);
    cleanups$3.push(function () {
      removeEvent(anchor$, 'click', clickHandler);
      container$.removeChild(anchor$);
    });
  }
  function addChannel(settings) {
    settings = processRules(settings, rules$4.addChannel, 'Channel.addChannel');
    openAddChannelPopup(settings);
  }
  function getAddChannelImgSrc(settings) {
    var filename = "friendadd_".concat(settings.size, "_yellow_rect.png");
    return "".concat(URL.channelIcon, "/channel/").concat(filename);
  }
  function openAddChannelPopup(settings) {
    var addChannelUrl = "".concat(URL.channel, "/").concat(settings.channelPublicId, "/friend");
    if (getAppKey$1() !== null) {
      addChannelUrl += "?".concat(makeChannelParams(API_VER$1));
    }
    windowOpen(addChannelUrl, ADD_CHANNEL_POPUP_NAME, POPUP_FEATURES);
  }
  function createChatButton$1(settings) {
    var container$ = getElement(settings.container);
    if (!container$) {
      throw new KakaoError('container is required for Channel.createChatButton: pass in element or id');
    } else {
      applyAttributes(settings, container$, {
        channelPublicId: 'data-channel-public-id',
        size: 'data-size',
        color: 'data-color',
        shape: 'data-shape',
        title: 'data-title',
        supportMultipleDensities: 'data-support-multiple-densities'
      });
    }
    settings = processRules(settings, rules$4.createChatButton, 'Channel.createChatButton');
    var imgSrc = getChatImgSrc$1(settings);
    var anchor$ = createAnchorImage$1(settings, imgSrc, '카카오톡 채널 1:1 채팅 버튼');
    container$.appendChild(anchor$);
    var clickHandler = function clickHandler(e) {
      e.preventDefault();
      openChatPopup$1(settings);
    };
    addEvent(anchor$, 'click', clickHandler);
    cleanups$3.push(function () {
      removeEvent(anchor$, 'click', clickHandler);
      container$.removeChild(anchor$);
    });
  }
  function chat$1(settings) {
    settings = processRules(settings, rules$4.chat, 'Channel.chat');
    openChatPopup$1(settings);
  }
  function getChatImgSrc$1(settings) {
    var filename = "".concat(settings.title, "_").concat(settings.size, "_").concat(settings.color, "_").concat(settings.shape, ".png");
    return "".concat(URL.channelIcon, "/channel/").concat(filename);
  }
  function openChatPopup$1(settings) {
    var chatUrl = "".concat(URL.channel, "/").concat(settings.channelPublicId, "/chat");
    if (getAppKey$1() !== null) {
      chatUrl += "?".concat(makeChannelParams(API_VER$1));
    }
    windowOpen(chatUrl, CHAT_POPUP_NAME$1, POPUP_FEATURES);
  }
  var cleanups$3 = [];
  function cleanup$4() {
    emptyCleanups(cleanups$3);
  }

  var request$4 = /*#__PURE__*/Object.freeze({
    __proto__: null,
    createAddChannelButton: createAddChannelButton,
    addChannel: addChannel,
    createChatButton: createChatButton$1,
    chat: chat$1,
    cleanup: cleanup$4
  });

  var Channel = makeModule([request$4]);

  var rules$3 = {
    createAddFriendButton: {
      required: {
        container: passesOneOf([isElement, isString]),
        plusFriendId: isString
      },
      optional: {
        size: isOneOf(['small', 'large']),
        color: isOneOf(['yellow', 'black']),
        shape: isOneOf(['rect', 'round']),
        supportMultipleDensities: isBoolean
      },
      defaults: {
        size: 'small',
        color: 'yellow',
        shape: 'rect',
        supportMultipleDensities: false
      }
    },
    addFriend: {
      required: {
        plusFriendId: isString
      }
    },
    createChatButton: {
      required: {
        container: passesOneOf([isElement, isString]),
        plusFriendId: isString
      },
      optional: {
        size: isOneOf(['small', 'large']),
        color: isOneOf(['yellow', 'mono']),
        shape: isOneOf(['pc', 'mobile']),
        title: isOneOf(['consult', 'question']),
        supportMultipleDensities: isBoolean
      },
      defaults: {
        size: 'small',
        color: 'yellow',
        shape: 'pc',
        title: 'consult',
        supportMultipleDensities: false
      }
    },
    chat: {
      required: {
        plusFriendId: isString
      }
    }
  };

  var API_VER = '1.0';
  var ADD_FRIEND_POPUP_NAME = 'plus_friend_add_social_plugin';
  var CHAT_POPUP_NAME = 'plus_friend_chat_social_plugin';
  function warnDeprecation() {
    if (console) {
      console.warn('KakaoWarning: Kakao.PlusFriend is deprecated. Please use Kakao.Channel instead.');
    }
  }
  function createAddFriendButton(settings) {
    warnDeprecation();
    var container$ = getElement(settings.container);
    if (!container$) {
      throw new KakaoError('container is required for PlusFriend.createAddFriendButton: pass in element or id');
    } else {
      applyAttributes(settings, container$, {
        plusFriendId: 'data-plusfriend-id',
        size: 'data-size',
        color: 'data-color',
        shape: 'data-shape',
        supportMultipleDensities: 'data-support-multiple-densities'
      });
    }
    settings = processRules(settings, rules$3.createAddFriendButton, 'PlusFriend.createAddFriendButton');
    var imgSrc = getAddFriendImgSrc(settings);
    var anchor$ = createAnchorImage$1(settings, imgSrc, '플러스친구 친구 추가 버튼');
    container$.appendChild(anchor$);
    var clickHandler = function clickHandler(e) {
      e.preventDefault();
      openAddFriendPopup(settings);
    };
    addEvent(anchor$, 'click', clickHandler);
    cleanups$2.push(function () {
      removeEvent(anchor$, 'click', clickHandler);
      container$.removeChild(anchor$);
    });
  }
  function addFriend(settings) {
    warnDeprecation();
    settings = processRules(settings, rules$3.addFriend, 'PlusFriend.addFriend');
    openAddFriendPopup(settings);
  }
  function getAddFriendImgSrc(settings) {
    var filename = "friendadd_".concat(settings.size, "_").concat(settings.color, "_").concat(settings.shape, ".png");
    return "".concat(URL.channelIcon, "/plusfriend/").concat(filename);
  }
  function openAddFriendPopup(settings) {
    var addFriendUrl = "".concat(URL.channel, "/").concat(settings.plusFriendId, "/friend");
    if (getAppKey$1() !== null) {
      addFriendUrl += "?".concat(makeChannelParams(API_VER));
    }
    windowOpen(addFriendUrl, ADD_FRIEND_POPUP_NAME, POPUP_FEATURES);
  }
  function createChatButton(settings) {
    warnDeprecation();
    var container$ = getElement(settings.container);
    if (!container$) {
      throw new KakaoError('container is required for PlusFriend.createChatButton: pass in element or id');
    } else {
      applyAttributes(settings, container$, {
        plusFriendId: 'data-plusfriend-id',
        size: 'data-size',
        color: 'data-color',
        shape: 'data-shape',
        title: 'data-title',
        supportMultipleDensities: 'data-support-multiple-densities'
      });
    }
    settings = processRules(settings, rules$3.createChatButton, 'PlusFriend.createChatButton');
    var imgSrc = getChatImgSrc(settings);
    var anchor$ = createAnchorImage$1(settings, imgSrc, '플러스친구 1:1 채팅 버튼');
    container$.appendChild(anchor$);
    var clickHandler = function clickHandler(e) {
      e.preventDefault();
      openChatPopup(settings);
    };
    addEvent(anchor$, 'click', clickHandler);
    cleanups$2.push(function () {
      removeEvent(anchor$, 'click', clickHandler);
      container$.removeChild(anchor$);
    });
  }
  function chat(settings) {
    warnDeprecation();
    settings = processRules(settings, rules$3.chat, 'PlusFriend.chat');
    openChatPopup(settings);
  }
  function getChatImgSrc(settings) {
    var filename = "".concat(settings.title, "_").concat(settings.size, "_").concat(settings.color, "_").concat(settings.shape, ".png");
    return "".concat(URL.channelIcon, "/plusfriend/").concat(filename);
  }
  function openChatPopup(settings) {
    var chatUrl = "".concat(URL.channel, "/").concat(settings.plusFriendId, "/chat");
    if (getAppKey$1() !== null) {
      chatUrl += "?".concat(makeChannelParams(API_VER));
    }
    windowOpen(chatUrl, CHAT_POPUP_NAME, POPUP_FEATURES);
  }
  var cleanups$2 = [];
  function cleanup$3() {
    emptyCleanups(cleanups$2);
  }

  var request$3 = /*#__PURE__*/Object.freeze({
    __proto__: null,
    createAddFriendButton: createAddFriendButton,
    addFriend: addFriend,
    createChatButton: createChatButton,
    chat: chat,
    cleanup: cleanup$3
  });

  var PlusFriend = makeModule([request$3]);

  var urlInfoRule = {
    required: {
      title: isString
    },
    optional: {
      desc: isString,
      name: isString,
      images: isArray,
      type: isString
    },
    defaults: {
      type: 'website'
    },
    after: function after(settings) {
      if (settings.images) {
        settings.imageurl = settings.images;
        delete settings.images;
      }
    }
  };
  var rules$2 = {
    createShareButton: {
      required: {
        container: passesOneOf([isElement, isString])
      },
      optional: {
        url: isString,
        text: isString
      },
      defaults: {
        url: location.href
      }
    },
    share: {
      optional: {
        url: isString,
        text: isString
      },
      defaults: {
        url: location.href
      }
    },
    open: {
      optional: {
        url: isString,
        text: isString,
        urlInfo: function urlInfo(obj) {
          return isObject(obj) && !!processRules(obj, urlInfoRule, 'Story.open');
        },
        install: isBoolean
      },
      defaults: {
        url: location.href,
        install: false
      }
    },
    createFollowButton: {
      required: {
        container: passesOneOf([isElement, isString]),
        id: isString
      },
      optional: {
        showFollowerCount: isBoolean,
        type: isOneOf(['horizontal', 'vertical'])
      },
      defaults: {
        showFollowerCount: true,
        type: 'horizontal'
      },
      after: function after(settings) {
        if (settings.id[0] !== '@') {
          settings.id = "@".concat(settings.id);
        }
      }
    }
  };

  function createShareButton(settings) {
    var container$ = getElement(settings.container);
    if (!container$) {
      throw new KakaoError('container is required for Story.createShareButton: pass in element or id');
    } else {
      applyAttributes(settings, container$, {
        url: 'data-url'
      });
    }
    settings = processRules(settings, rules$2.createShareButton, 'Story.createShareButton');
    var anchor$ = createAnchorImage(URL.storyIcon, '카카오스토리 웹 공유 버튼');
    container$.appendChild(anchor$);
    var clickHandler = function clickHandler(e) {
      e.preventDefault();
      openSharePopup(settings);
    };
    addEvent(anchor$, 'click', clickHandler);
    cleanups$1.push(function () {
      removeEvent(anchor$, 'click', clickHandler);
      container$.removeChild(anchor$);
    });
  }
  function createAnchorImage(imgSrc, imgTitle) {
    var a$ = document.createElement('a');
    a$.setAttribute('href', '#');
    var img$ = document.createElement('img');
    img$.setAttribute('src', imgSrc);
    img$.setAttribute('title', imgTitle);
    img$.setAttribute('alt', imgTitle);
    a$.appendChild(img$);
    return a$;
  }
  function share$1(settings) {
    settings = processRules(settings, rules$2.share, 'Story.share');
    openSharePopup(settings);
  }
  function openSharePopup(settings) {
    var params = assignIn({
      url: settings.url
    }, makeStoryParams());
    if (settings.text) {
      params.text = settings.text;
    }
    windowOpen("".concat(URL.storyShare, "?").concat(buildQueryString(params)), 'kakaostory_social_plugin', 'width=670, height=800, scrollbars=yes');
  }
  function open(settings) {
    settings = processRules(settings, rules$2.open, 'Story.open');
    var storyPostScheme = makeStoryPostScheme(settings);
    var androidIntent = ["intent:".concat(storyPostScheme, "#Intent"), "".concat(settings.install ? 'package=com.kakao.story;' : '', "end;")].join(';');
    var web2appOptions = {
      urlScheme: storyPostScheme,
      intentURI: androidIntent,
      appName: 'KakaoStory',
      storeURL: getInstallUrl('com.kakao.story', '486244601'),
      onUnsupportedEnvironment: function onUnsupportedEnvironment() {
        settings.fail && settings.fail();
      }
    };
    try {
      web2app(web2appOptions);
    } catch (error) {
    }
  }
  function makeStoryPostScheme(settings) {
    var domain = location.hostname || '';
    var params = assignIn({
      apiver: '1.0',
      appver: VERSION,
      appid: domain,
      appname: domain,
      post: settings.text ? "".concat(settings.text, "\n").concat(settings.url) : settings.url
    }, makeStoryParams());
    if (settings.urlInfo) {
      params.urlinfo = JSON.stringify(settings.urlInfo);
      params.appname = settings.urlInfo.name || params.appname;
    }
    return "".concat(URL.storyPostScheme, "?").concat(buildQueryString(params));
  }
  function createFollowButton(settings) {
    var container$ = getElement(settings.container);
    if (!container$) {
      throw new KakaoError('container is required for Story.createFollowButton: pass in element or id');
    } else {
      applyAttributes(settings, container$, {
        id: 'data-id',
        showFollowerCount: 'data-show-follower-count',
        type: 'data-type'
      });
    }
    settings = processRules(settings, rules$2.createFollowButton, 'Story.createFollowButton');
    var _createStoryFollowIfr = createStoryFollowIframe(settings),
        iframe$ = _createStoryFollowIfr.iframe$,
        messageHandler = _createStoryFollowIfr.messageHandler;
    container$.appendChild(iframe$);
    addEvent(window, 'message', messageHandler);
    cleanups$1.push(function () {
      removeEvent(window, 'message', messageHandler);
      container$.removeChild(iframe$);
    });
  }
  var _storyFollowIframeId = 0;
  function createStoryFollowIframe(settings) {
    var iframeId = _storyFollowIframeId++;
    var iframeWidth = settings.showFollowerCount && settings.type === 'horizontal' ? 85 : 59;
    var iframeHeight = settings.showFollowerCount && settings.type === 'vertical' ? 46 : 20;
    var iframe$ = document.createElement('iframe');
    iframe$.src = makeStoryFollowUrl(settings, iframeId);
    iframe$.setAttribute('frameborder', '0');
    iframe$.setAttribute('marginwidth', '0');
    iframe$.setAttribute('marginheight', '0');
    iframe$.setAttribute('scrolling', 'no');
    iframe$.setAttribute('style', "width:".concat(iframeWidth, "px; height:").concat(iframeHeight, "px;"));
    var messageHandler = function messageHandler(e) {
      if (e.data && /\.kakao\.com$/.test(e.origin) && typeof e.data === 'string') {
        var _map = map(e.data.split(','), function (e) {
          return parseInt(e, 10);
        }),
            _map2 = _slicedToArray(_map, 3),
            originIframeId = _map2[0],
            afterWidth = _map2[1],
            afterHeight = _map2[2];
        if (originIframeId === iframeId) {
          if (iframeWidth !== afterWidth) {
            iframe$.style.width = "".concat(afterWidth, "px");
          }
          if (iframeHeight !== afterHeight) {
            iframe$.style.height = "".concat(afterHeight, "px");
          }
        }
      }
    };
    return {
      iframe$: iframe$,
      messageHandler: messageHandler
    };
  }
  function makeStoryFollowUrl(settings, iframeId) {
    var params = assignIn({
      id: settings.id,
      type: settings.type,
      hideFollower: !settings.showFollowerCount,
      frameId: iframeId
    }, makeStoryParams());
    return "".concat(URL.storyChannelFollow, "?").concat(buildQueryString(params));
  }
  function makeStoryParams() {
    var params = {
      kakao_agent: KAKAO_AGENT
    };
    if (getAppKey$1() !== null) {
      params.app_key = getAppKey$1();
    }
    return params;
  }
  var cleanups$1 = [];
  function cleanup$2() {
    emptyCleanups(cleanups$1);
  }

  var request$2 = /*#__PURE__*/Object.freeze({
    __proto__: null,
    createShareButton: createShareButton,
    share: share$1,
    open: open,
    createFollowButton: createFollowButton,
    cleanup: cleanup$2
  });

  var Story = makeModule([request$2]);

  var coordTypes = ['wgs84', 'katec'];
  var vehicleTypes = [1, 2, 3, 4, 5, 6, 7];
  var rpOptions = [1, 2, 3, 4, 5, 6, 8, 100];
  var viaPointRule = {
    required: {
      name: isString,
      x: isNumber,
      y: isNumber
    },
    optional: {
      rpflag: isString,
      cid: isString
    }
  };
  var rules$1 = {
    start: {
      required: {
        name: isString,
        x: isNumber,
        y: isNumber
      },
      optional: {
        coordType: isOneOf(coordTypes),
        vehicleType: isOneOf(vehicleTypes),
        rpOption: isOneOf(rpOptions),
        routeInfo: isBoolean,
        sX: isNumber,
        sY: isNumber,
        sAngle: isNumber,
        returnUri: isString,
        rpflag: isString,
        cid: isString,
        guideId: isNumber,
        viaPoints: function viaPoints(points) {
          if (!isArray(points)) {
            return false;
          } else if (points.length > 3) {
            throw new KakaoError('Invalid parameter keys: via points should not be exceed 3. at Navi.start');
          } else {
            forEach(points, function (point) {
              return processRules(point, viaPointRule, 'Navi.start');
            });
            return true;
          }
        }
      },
      defaults: {
        coordType: 'katec',
        vehicleType: 1,
        rpOption: 100,
        routeInfo: false
      }
    },
    share: {
      required: {
        name: isString,
        x: isNumber,
        y: isNumber
      },
      optional: {
        coordType: isOneOf(coordTypes),
        rpflag: isString,
        cid: isString,
        guideId: isNumber
      },
      defaults: {
        coordType: 'katec'
      }
    }
  };

  function start(settings) {
    settings = processRules(settings, rules$1.start, 'Navi.start');
    var naviStartParams = buildQueryString(makeNaviStartParams(settings));
    var naviScheme = "".concat(URL.naviStartScheme, "?").concat(naviStartParams);
    var fallbackUrl = "".concat(URL.naviWeb, "?").concat(naviStartParams);
    callWeb2app(naviScheme, fallbackUrl);
  }
  function makeNaviStartParams(settings) {
    var destination = {
      name: settings.name,
      x: settings.x,
      y: settings.y,
      rpflag: settings.rpflag,
      cid: settings.cid,
      guide_id: settings.guideId
    };
    var option = {
      coord_type: settings.coordType,
      vehicle_type: settings.vehicleType,
      rpoption: settings.rpOption,
      route_info: settings.routeInfo,
      s_x: settings.sX,
      s_y: settings.sY,
      s_angle: settings.sAngle,
      return_uri: settings.returnUri
    };
    var params = makeNaviParams();
    params.param = {
      destination: destination,
      option: option,
      via_list: settings.viaPoints
    };
    return params;
  }
  function share(settings) {
    settings = processRules(settings, rules$1.share, 'Navi.share');
    var naviShareParams = buildQueryString(makeNaviShareParams(settings));
    var naviScheme = "".concat(URL.naviShareScheme, "?").concat(naviShareParams);
    var fallbackUrl = "".concat(URL.naviWeb, "?").concat(naviShareParams);
    callWeb2app(naviScheme, fallbackUrl);
  }
  function makeNaviShareParams(settings) {
    var destination = {
      name: settings.name,
      x: settings.x,
      y: settings.y,
      rpflag: settings.rpflag,
      cid: settings.cid,
      guide_id: settings.guideId
    };
    var option = {
      coord_type: settings.coordType
    };
    var params = makeNaviParams();
    params.param = {
      destination: destination,
      option: option
    };
    params.scheme_type = 'sharePoi';
    return params;
  }
  function makeNaviParams() {
    return {
      appkey: getAppKey$1(),
      apiver: '1.0',
      extras: {
        KA: KAKAO_AGENT
      }
    };
  }
  function callWeb2app(naviScheme, fallbackUrl) {
    var androidIntent = ["intent:".concat(naviScheme, "#Intent"), 'package=com.locnall.KimGiSa', "S.browser_fallback_url=".concat(encodeURIComponent(fallbackUrl)), 'end;'].join(';');
    var web2appOptions = {
      urlScheme: naviScheme,
      intentURI: androidIntent,
      storeURL: fallbackUrl,
      universalLink: fallbackUrl
    };
    try {
      web2app(web2appOptions);
    } catch (error) {
    }
  }

  var request$1 = /*#__PURE__*/Object.freeze({
    __proto__: null,
    start: start,
    share: share
  });

  var Navi = makeModule([request$1]);

  function pickableCountValidator(n) {
    return isInteger(n) && n > 0 && n < 101;
  }
  function checkPickableCounts(settings) {
    if (settings.maxPickableCount < settings.minPickableCount) {
      throw new KakaoError('"minPickableCount" should not larger than "maxPickableCount"');
    }
  }
  function disableSelectOptionsValidator(arr) {
    var disableSelectOptionRule = {
      required: {
        reason: isOneOf(['msgBlocked', 'registered', 'unregistered', 'notFriend', 'custom'])
      },
      optional: {
        message: isString,
        uuids: isArray
      },
      after: function after(settings) {
        if (settings.reason === 'custom' && (!settings.message || !settings.uuids)) {
          throw new KakaoError('"message" and "uuids" must be set for "custom" option in disableSelectOption');
        }
      }
    };
    return isArray(arr) && every(arr, function (e) {
      return isObject(e) && !!processRules(e, disableSelectOptionRule, 'disableSelectOption');
    });
  }
  function checkPickerChatFilters(settings) {
    if (settings.selectionType === 'chatMember') {
      var f = settings.chatFilters;
      if (f.indexOf('open') > -1) {
        throw new KakaoError('"open" is not allowed in "chatFilters"');
      }
      if ((f.indexOf('direct') > -1 || f.indexOf('multi') > -1) && f.indexOf('regular') === -1) {
        throw new KakaoError('"regular" should be included in "chatFilters"');
      }
    }
  }
  var friendFilters = ['none', 'invitable', 'registered'];
  var serviceTypeFilters = ['talk', 'story', 'talkstory'];
  var friendOrders = ['age'];
  var selectionTypes = ['chat', 'chatMember'];
  var _chatFilters = ['regular', 'direct', 'multi', 'open'];
  var osFilters = ['all', 'ios', 'android'];
  var friendPickerOptional = {
    returnUrl: isString,
    success: isFunction,
    fail: isFunction,
    always: isFunction,
    friendFilter: isOneOf(friendFilters),
    serviceTypeFilter: isOneOf(serviceTypeFilters),
    friendOrder: isOneOf(friendOrders),
    title: isString,
    enableSearch: isBoolean,
    countryCodeFilters: isArray,
    usingOsFilter: isOneOf(osFilters),
    showMyProfile: isBoolean,
    showFavorite: isBoolean,
    disableSelectOptions: disableSelectOptionsValidator,
    displayAllProfile: isBoolean
  };
  var friendPickerDefault = {
    success: emptyFunc,
    fail: emptyFunc,
    always: emptyFunc,
    friendFilter: friendFilters[0],
    serviceTypeFilter: serviceTypeFilters[0]
  };
  var friendsParamsRule = {
    optional: {
      friendFilter: isOneOf(friendFilters),
      serviceTypeFilter: isOneOf(serviceTypeFilters),
      friendOrder: isOneOf(friendOrders),
      countryCodeFilters: isArray,
      usingOsFilter: isOneOf(osFilters),
      showMyProfile: isBoolean,
      showFavorite: isBoolean,
      showPickedFriend: isBoolean
    },
    defaults: {
      friendFilter: friendFilters[0],
      serviceTypeFilter: serviceTypeFilters[0]
    }
  };
  var chatParamsRule = {
    optional: {
      selectionType: isOneOf(selectionTypes),
      chatFilters: function chatFilters(arr) {
        return isArray(arr) && every(arr, function (e) {
          return isOneOf(_chatFilters)(e);
        });
      }
    },
    defaults: {
      selectionType: selectionTypes[0],
      chatFilters: [_chatFilters[0]]
    },
    after: checkPickerChatFilters
  };
  var rules = {
    selectFriend: {
      optional: friendPickerOptional,
      defaults: friendPickerDefault
    },
    selectFriends: {
      optional: _objectSpread2(_objectSpread2({}, friendPickerOptional), {}, {
        showPickedFriend: isBoolean,
        maxPickableCount: pickableCountValidator,
        minPickableCount: pickableCountValidator
      }),
      defaults: friendPickerDefault,
      after: checkPickableCounts
    },
    selectChat: {
      optional: {
        returnUrl: isString,
        success: isFunction,
        fail: isFunction,
        always: isFunction,
        selectionType: isOneOf(selectionTypes),
        chatFilters: function chatFilters(arr) {
          return isArray(arr) && every(arr, function (e) {
            return isOneOf(_chatFilters)(e);
          });
        },
        title: isString,
        enableSearch: isBoolean,
        disableSelectOptions: disableSelectOptionsValidator,
        displayAllProfile: isBoolean,
        maxPickableCount: pickableCountValidator,
        minPickableCount: pickableCountValidator
      },
      defaults: {
        success: emptyFunc,
        fail: emptyFunc,
        always: emptyFunc,
        selectionType: selectionTypes[0],
        chatFilters: [_chatFilters[0]]
      },
      after: function after(settings) {
        checkPickableCounts(settings);
        checkPickerChatFilters(settings);
      }
    },
    select: {
      optional: {
        returnUrl: isString,
        success: isFunction,
        fail: isFunction,
        always: isFunction,
        title: isString,
        enableSearch: isBoolean,
        disableSelectOptions: disableSelectOptionsValidator,
        displayAllProfile: isBoolean,
        maxPickableCount: pickableCountValidator,
        minPickableCount: pickableCountValidator,
        friendsParams: function friendsParams(obj) {
          return isObject(obj) && !!processRules(obj, friendsParamsRule, 'Picker.select');
        },
        chatParams: function chatParams(obj) {
          return isObject(obj) && !!processRules(obj, chatParamsRule, 'Picker.select');
        }
      },
      defaults: {
        success: emptyFunc,
        fail: emptyFunc,
        always: emptyFunc
      },
      after: checkPickableCounts
    }
  };

  function selectFriends(settings) {
    settings = processRules(settings, rules.selectFriends, 'Picker.selectFriends');
    requestPicker(settings, pruneNeedlessParams(settings), '/select/multiple');
  }
  function selectFriend(settings) {
    settings = processRules(settings, rules.selectFriend, 'Picker.selectFriend');
    requestPicker(settings, pruneNeedlessParams(settings), '/select/single');
  }
  function selectChat(settings) {
    settings = processRules(settings, rules.selectChat, 'Picker.selectChat');
    requestPicker(settings, pruneNeedlessParams(settings), '/chat/select');
  }
  function select(settings) {
    settings = processRules(settings, rules.select, 'Picker.select');
    var params = _objectSpread2(_objectSpread2(_objectSpread2({}, pruneNeedlessParams(settings)), settings.friendsParams), settings.chatParams);
    requestPicker(settings, params, '/tab/select');
  }
  function requestPicker(settings, params, subpath) {
    cleanup$1();
    var transId = generateTxId();
    var pickerParams = _objectSpread2(_objectSpread2({
      transId: transId,
      appKey: getAppKey$1(),
      ka: KAKAO_AGENT
    }, getAccessToken() && {
      token: getAccessToken()
    }), formatParams(params));
    var url = URL.pickerDomain + subpath;
    if (settings.returnUrl) {
      pickerParams.returnUrl = settings.returnUrl;
      createAndSubmitForm(pickerParams, url);
    } else {
      createHiddenIframe(transId, "".concat(URL.pickerDomain, "/proxy?transId=").concat(transId), cleanups);
      addMessageEvent(settings, URL.pickerDomain, cleanups);
      openPopupAndSubmitForm(pickerParams, {
        url: url,
        popupName: 'friend_picker',
        popupFeatures: "location=no,resizable=no,status=no,scrollbars=no,width=460,height=608"
      });
    }
  }
  function pruneNeedlessParams(settings) {
    var cloned = _objectSpread2({}, settings);
    var keysNeedPrune = ['returnUrl', 'success', 'fail', 'always', 'friendsParams', 'chatParams'];
    keysNeedPrune.forEach(function (key) {
      if (cloned[key]) {
        delete cloned[key];
      }
    });
    return cloned;
  }
  function formatParams(params) {
    var keysNeedConvertToCSV = ['countryCodeFilters', 'chatFilters'];
    keysNeedConvertToCSV.forEach(function (key) {
      if (params[key] !== undefined) {
        params[key] = params[key].join(',');
      }
    });
    if (params.disableSelectOptions) {
      params.disableSelectOptions = JSON.stringify(params.disableSelectOptions);
    }
    return params;
  }
  var cleanups = [];
  function cleanup$1() {
    emptyCleanups(cleanups);
  }

  var request = /*#__PURE__*/Object.freeze({
    __proto__: null,
    selectFriends: selectFriends,
    selectFriend: selectFriend,
    selectChat: selectChat,
    select: select,
    cleanup: cleanup$1
  });

  var Picker = makeModule([request]);

  if (typeof define === 'function' && define.amd) {
    window.Kakao = exports;
  }
  if (typeof window.kakaoAsyncInit === 'function') {
    setTimeout(function () {
      window.kakaoAsyncInit();
    }, 0);
  }
  function init(appKey) {
    if (UA.browser.msie && UA.browser.version.major < 9) {
      throw new KakaoError('Kakao.init: Unsupported browser');
    }
    if (isInitialized()) {
      throw new KakaoError('Kakao.init: Already initialized');
    }
    if (!isString(appKey)) {
      throw new KakaoError('Kakao.init: App key must be provided');
    }
    setAppKey(appKey);
    {
      this.Auth = Auth;
      this.API = API;
      this.Link = Link;
      this.Channel = Channel;
      this.PlusFriend = PlusFriend;
      this.Story = Story;
      this.Navi = Navi;
      this.Picker = Picker;
    }
  }
  function isInitialized() {
    return getAppKey$1() !== null;
  }
  function cleanup() {
    var _this = this;
    forEach(['Auth', 'API', 'Link', 'Channel', 'PlusFriend', 'Story', 'Navi', 'Picker'], function (e) {
      return _this[e] && _this[e].cleanup();
    });
    setAppKey(null);
  }

  exports.VERSION = VERSION;
  exports.cleanup = cleanup;
  exports.init = init;
  exports.isInitialized = isInitialized;

  Object.defineProperty(exports, '__esModule', { value: true });

})));
