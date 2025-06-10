package com.example.combined.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * EmployeeService
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.70.0)",
    comments = "Source: Schema.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class EmployeeServiceGrpc {

  private EmployeeServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "EmployeeService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.combined.grpc.CreateEmployeeRequest,
      com.example.combined.grpc.EmployeeResponse> getCreateEmployeeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateEmployee",
      requestType = com.example.combined.grpc.CreateEmployeeRequest.class,
      responseType = com.example.combined.grpc.EmployeeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.combined.grpc.CreateEmployeeRequest,
      com.example.combined.grpc.EmployeeResponse> getCreateEmployeeMethod() {
    io.grpc.MethodDescriptor<com.example.combined.grpc.CreateEmployeeRequest, com.example.combined.grpc.EmployeeResponse> getCreateEmployeeMethod;
    if ((getCreateEmployeeMethod = EmployeeServiceGrpc.getCreateEmployeeMethod) == null) {
      synchronized (EmployeeServiceGrpc.class) {
        if ((getCreateEmployeeMethod = EmployeeServiceGrpc.getCreateEmployeeMethod) == null) {
          EmployeeServiceGrpc.getCreateEmployeeMethod = getCreateEmployeeMethod =
              io.grpc.MethodDescriptor.<com.example.combined.grpc.CreateEmployeeRequest, com.example.combined.grpc.EmployeeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateEmployee"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.CreateEmployeeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EmployeeServiceMethodDescriptorSupplier("CreateEmployee"))
              .build();
        }
      }
    }
    return getCreateEmployeeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByIdRequest,
      com.example.combined.grpc.EmployeeResponse> getGetEmployeeByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetEmployeeById",
      requestType = com.example.combined.grpc.EmployeeByIdRequest.class,
      responseType = com.example.combined.grpc.EmployeeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByIdRequest,
      com.example.combined.grpc.EmployeeResponse> getGetEmployeeByIdMethod() {
    io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByIdRequest, com.example.combined.grpc.EmployeeResponse> getGetEmployeeByIdMethod;
    if ((getGetEmployeeByIdMethod = EmployeeServiceGrpc.getGetEmployeeByIdMethod) == null) {
      synchronized (EmployeeServiceGrpc.class) {
        if ((getGetEmployeeByIdMethod = EmployeeServiceGrpc.getGetEmployeeByIdMethod) == null) {
          EmployeeServiceGrpc.getGetEmployeeByIdMethod = getGetEmployeeByIdMethod =
              io.grpc.MethodDescriptor.<com.example.combined.grpc.EmployeeByIdRequest, com.example.combined.grpc.EmployeeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetEmployeeById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeeByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EmployeeServiceMethodDescriptorSupplier("GetEmployeeById"))
              .build();
        }
      }
    }
    return getGetEmployeeByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByRoleRequest,
      com.example.combined.grpc.EmployeesResponse> getGetEmployeesByRoleMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetEmployeesByRole",
      requestType = com.example.combined.grpc.EmployeeByRoleRequest.class,
      responseType = com.example.combined.grpc.EmployeesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByRoleRequest,
      com.example.combined.grpc.EmployeesResponse> getGetEmployeesByRoleMethod() {
    io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByRoleRequest, com.example.combined.grpc.EmployeesResponse> getGetEmployeesByRoleMethod;
    if ((getGetEmployeesByRoleMethod = EmployeeServiceGrpc.getGetEmployeesByRoleMethod) == null) {
      synchronized (EmployeeServiceGrpc.class) {
        if ((getGetEmployeesByRoleMethod = EmployeeServiceGrpc.getGetEmployeesByRoleMethod) == null) {
          EmployeeServiceGrpc.getGetEmployeesByRoleMethod = getGetEmployeesByRoleMethod =
              io.grpc.MethodDescriptor.<com.example.combined.grpc.EmployeeByRoleRequest, com.example.combined.grpc.EmployeesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetEmployeesByRole"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeeByRoleRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EmployeeServiceMethodDescriptorSupplier("GetEmployeesByRole"))
              .build();
        }
      }
    }
    return getGetEmployeesByRoleMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeBySalaryRequest,
      com.example.combined.grpc.EmployeesResponse> getGetEmployeesBySalaryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetEmployeesBySalary",
      requestType = com.example.combined.grpc.EmployeeBySalaryRequest.class,
      responseType = com.example.combined.grpc.EmployeesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeBySalaryRequest,
      com.example.combined.grpc.EmployeesResponse> getGetEmployeesBySalaryMethod() {
    io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeBySalaryRequest, com.example.combined.grpc.EmployeesResponse> getGetEmployeesBySalaryMethod;
    if ((getGetEmployeesBySalaryMethod = EmployeeServiceGrpc.getGetEmployeesBySalaryMethod) == null) {
      synchronized (EmployeeServiceGrpc.class) {
        if ((getGetEmployeesBySalaryMethod = EmployeeServiceGrpc.getGetEmployeesBySalaryMethod) == null) {
          EmployeeServiceGrpc.getGetEmployeesBySalaryMethod = getGetEmployeesBySalaryMethod =
              io.grpc.MethodDescriptor.<com.example.combined.grpc.EmployeeBySalaryRequest, com.example.combined.grpc.EmployeesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetEmployeesBySalary"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeeBySalaryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EmployeeServiceMethodDescriptorSupplier("GetEmployeesBySalary"))
              .build();
        }
      }
    }
    return getGetEmployeesBySalaryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByDistrictRequest,
      com.example.combined.grpc.EmployeesResponse> getGetEmployeesByDistrictMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetEmployeesByDistrict",
      requestType = com.example.combined.grpc.EmployeeByDistrictRequest.class,
      responseType = com.example.combined.grpc.EmployeesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByDistrictRequest,
      com.example.combined.grpc.EmployeesResponse> getGetEmployeesByDistrictMethod() {
    io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByDistrictRequest, com.example.combined.grpc.EmployeesResponse> getGetEmployeesByDistrictMethod;
    if ((getGetEmployeesByDistrictMethod = EmployeeServiceGrpc.getGetEmployeesByDistrictMethod) == null) {
      synchronized (EmployeeServiceGrpc.class) {
        if ((getGetEmployeesByDistrictMethod = EmployeeServiceGrpc.getGetEmployeesByDistrictMethod) == null) {
          EmployeeServiceGrpc.getGetEmployeesByDistrictMethod = getGetEmployeesByDistrictMethod =
              io.grpc.MethodDescriptor.<com.example.combined.grpc.EmployeeByDistrictRequest, com.example.combined.grpc.EmployeesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetEmployeesByDistrict"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeeByDistrictRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EmployeeServiceMethodDescriptorSupplier("GetEmployeesByDistrict"))
              .build();
        }
      }
    }
    return getGetEmployeesByDistrictMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByBranchRequest,
      com.example.combined.grpc.EmployeesResponse> getGetEmployeesByBranchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetEmployeesByBranch",
      requestType = com.example.combined.grpc.EmployeeByBranchRequest.class,
      responseType = com.example.combined.grpc.EmployeesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByBranchRequest,
      com.example.combined.grpc.EmployeesResponse> getGetEmployeesByBranchMethod() {
    io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByBranchRequest, com.example.combined.grpc.EmployeesResponse> getGetEmployeesByBranchMethod;
    if ((getGetEmployeesByBranchMethod = EmployeeServiceGrpc.getGetEmployeesByBranchMethod) == null) {
      synchronized (EmployeeServiceGrpc.class) {
        if ((getGetEmployeesByBranchMethod = EmployeeServiceGrpc.getGetEmployeesByBranchMethod) == null) {
          EmployeeServiceGrpc.getGetEmployeesByBranchMethod = getGetEmployeesByBranchMethod =
              io.grpc.MethodDescriptor.<com.example.combined.grpc.EmployeeByBranchRequest, com.example.combined.grpc.EmployeesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetEmployeesByBranch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeeByBranchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EmployeeServiceMethodDescriptorSupplier("GetEmployeesByBranch"))
              .build();
        }
      }
    }
    return getGetEmployeesByBranchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.combined.grpc.Empty,
      com.example.combined.grpc.EmployeesResponse> getGetAllEmployeesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAllEmployees",
      requestType = com.example.combined.grpc.Empty.class,
      responseType = com.example.combined.grpc.EmployeesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.combined.grpc.Empty,
      com.example.combined.grpc.EmployeesResponse> getGetAllEmployeesMethod() {
    io.grpc.MethodDescriptor<com.example.combined.grpc.Empty, com.example.combined.grpc.EmployeesResponse> getGetAllEmployeesMethod;
    if ((getGetAllEmployeesMethod = EmployeeServiceGrpc.getGetAllEmployeesMethod) == null) {
      synchronized (EmployeeServiceGrpc.class) {
        if ((getGetAllEmployeesMethod = EmployeeServiceGrpc.getGetAllEmployeesMethod) == null) {
          EmployeeServiceGrpc.getGetAllEmployeesMethod = getGetAllEmployeesMethod =
              io.grpc.MethodDescriptor.<com.example.combined.grpc.Empty, com.example.combined.grpc.EmployeesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAllEmployees"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EmployeeServiceMethodDescriptorSupplier("GetAllEmployees"))
              .build();
        }
      }
    }
    return getGetAllEmployeesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByRoleAndBranchRequest,
      com.example.combined.grpc.EmployeesResponse> getGetEmployeesByRoleAndBranchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetEmployeesByRoleAndBranch",
      requestType = com.example.combined.grpc.EmployeeByRoleAndBranchRequest.class,
      responseType = com.example.combined.grpc.EmployeesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByRoleAndBranchRequest,
      com.example.combined.grpc.EmployeesResponse> getGetEmployeesByRoleAndBranchMethod() {
    io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByRoleAndBranchRequest, com.example.combined.grpc.EmployeesResponse> getGetEmployeesByRoleAndBranchMethod;
    if ((getGetEmployeesByRoleAndBranchMethod = EmployeeServiceGrpc.getGetEmployeesByRoleAndBranchMethod) == null) {
      synchronized (EmployeeServiceGrpc.class) {
        if ((getGetEmployeesByRoleAndBranchMethod = EmployeeServiceGrpc.getGetEmployeesByRoleAndBranchMethod) == null) {
          EmployeeServiceGrpc.getGetEmployeesByRoleAndBranchMethod = getGetEmployeesByRoleAndBranchMethod =
              io.grpc.MethodDescriptor.<com.example.combined.grpc.EmployeeByRoleAndBranchRequest, com.example.combined.grpc.EmployeesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetEmployeesByRoleAndBranch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeeByRoleAndBranchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EmployeeServiceMethodDescriptorSupplier("GetEmployeesByRoleAndBranch"))
              .build();
        }
      }
    }
    return getGetEmployeesByRoleAndBranchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByDistrictAndBranchRequest,
      com.example.combined.grpc.EmployeesResponse> getGetEmployeesByDistrictAndBranchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetEmployeesByDistrictAndBranch",
      requestType = com.example.combined.grpc.EmployeeByDistrictAndBranchRequest.class,
      responseType = com.example.combined.grpc.EmployeesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByDistrictAndBranchRequest,
      com.example.combined.grpc.EmployeesResponse> getGetEmployeesByDistrictAndBranchMethod() {
    io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByDistrictAndBranchRequest, com.example.combined.grpc.EmployeesResponse> getGetEmployeesByDistrictAndBranchMethod;
    if ((getGetEmployeesByDistrictAndBranchMethod = EmployeeServiceGrpc.getGetEmployeesByDistrictAndBranchMethod) == null) {
      synchronized (EmployeeServiceGrpc.class) {
        if ((getGetEmployeesByDistrictAndBranchMethod = EmployeeServiceGrpc.getGetEmployeesByDistrictAndBranchMethod) == null) {
          EmployeeServiceGrpc.getGetEmployeesByDistrictAndBranchMethod = getGetEmployeesByDistrictAndBranchMethod =
              io.grpc.MethodDescriptor.<com.example.combined.grpc.EmployeeByDistrictAndBranchRequest, com.example.combined.grpc.EmployeesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetEmployeesByDistrictAndBranch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeeByDistrictAndBranchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EmployeeServiceMethodDescriptorSupplier("GetEmployeesByDistrictAndBranch"))
              .build();
        }
      }
    }
    return getGetEmployeesByDistrictAndBranchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByMinSalaryRequest,
      com.example.combined.grpc.EmployeesResponse> getGetEmployeesWithSalaryGreaterThanMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetEmployeesWithSalaryGreaterThan",
      requestType = com.example.combined.grpc.EmployeeByMinSalaryRequest.class,
      responseType = com.example.combined.grpc.EmployeesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByMinSalaryRequest,
      com.example.combined.grpc.EmployeesResponse> getGetEmployeesWithSalaryGreaterThanMethod() {
    io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByMinSalaryRequest, com.example.combined.grpc.EmployeesResponse> getGetEmployeesWithSalaryGreaterThanMethod;
    if ((getGetEmployeesWithSalaryGreaterThanMethod = EmployeeServiceGrpc.getGetEmployeesWithSalaryGreaterThanMethod) == null) {
      synchronized (EmployeeServiceGrpc.class) {
        if ((getGetEmployeesWithSalaryGreaterThanMethod = EmployeeServiceGrpc.getGetEmployeesWithSalaryGreaterThanMethod) == null) {
          EmployeeServiceGrpc.getGetEmployeesWithSalaryGreaterThanMethod = getGetEmployeesWithSalaryGreaterThanMethod =
              io.grpc.MethodDescriptor.<com.example.combined.grpc.EmployeeByMinSalaryRequest, com.example.combined.grpc.EmployeesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetEmployeesWithSalaryGreaterThan"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeeByMinSalaryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EmployeeServiceMethodDescriptorSupplier("GetEmployeesWithSalaryGreaterThan"))
              .build();
        }
      }
    }
    return getGetEmployeesWithSalaryGreaterThanMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeBySalaryRangeRequest,
      com.example.combined.grpc.EmployeesResponse> getGetEmployeesWithSalaryBetweenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetEmployeesWithSalaryBetween",
      requestType = com.example.combined.grpc.EmployeeBySalaryRangeRequest.class,
      responseType = com.example.combined.grpc.EmployeesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeBySalaryRangeRequest,
      com.example.combined.grpc.EmployeesResponse> getGetEmployeesWithSalaryBetweenMethod() {
    io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeBySalaryRangeRequest, com.example.combined.grpc.EmployeesResponse> getGetEmployeesWithSalaryBetweenMethod;
    if ((getGetEmployeesWithSalaryBetweenMethod = EmployeeServiceGrpc.getGetEmployeesWithSalaryBetweenMethod) == null) {
      synchronized (EmployeeServiceGrpc.class) {
        if ((getGetEmployeesWithSalaryBetweenMethod = EmployeeServiceGrpc.getGetEmployeesWithSalaryBetweenMethod) == null) {
          EmployeeServiceGrpc.getGetEmployeesWithSalaryBetweenMethod = getGetEmployeesWithSalaryBetweenMethod =
              io.grpc.MethodDescriptor.<com.example.combined.grpc.EmployeeBySalaryRangeRequest, com.example.combined.grpc.EmployeesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetEmployeesWithSalaryBetween"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeeBySalaryRangeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EmployeeServiceMethodDescriptorSupplier("GetEmployeesWithSalaryBetween"))
              .build();
        }
      }
    }
    return getGetEmployeesWithSalaryBetweenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByRoleAndMinSalaryRequest,
      com.example.combined.grpc.EmployeesResponse> getGetEmployeesByRoleAndMinSalaryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetEmployeesByRoleAndMinSalary",
      requestType = com.example.combined.grpc.EmployeeByRoleAndMinSalaryRequest.class,
      responseType = com.example.combined.grpc.EmployeesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByRoleAndMinSalaryRequest,
      com.example.combined.grpc.EmployeesResponse> getGetEmployeesByRoleAndMinSalaryMethod() {
    io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByRoleAndMinSalaryRequest, com.example.combined.grpc.EmployeesResponse> getGetEmployeesByRoleAndMinSalaryMethod;
    if ((getGetEmployeesByRoleAndMinSalaryMethod = EmployeeServiceGrpc.getGetEmployeesByRoleAndMinSalaryMethod) == null) {
      synchronized (EmployeeServiceGrpc.class) {
        if ((getGetEmployeesByRoleAndMinSalaryMethod = EmployeeServiceGrpc.getGetEmployeesByRoleAndMinSalaryMethod) == null) {
          EmployeeServiceGrpc.getGetEmployeesByRoleAndMinSalaryMethod = getGetEmployeesByRoleAndMinSalaryMethod =
              io.grpc.MethodDescriptor.<com.example.combined.grpc.EmployeeByRoleAndMinSalaryRequest, com.example.combined.grpc.EmployeesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetEmployeesByRoleAndMinSalary"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeeByRoleAndMinSalaryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EmployeeServiceMethodDescriptorSupplier("GetEmployeesByRoleAndMinSalary"))
              .build();
        }
      }
    }
    return getGetEmployeesByRoleAndMinSalaryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByDistrictOrderedRequest,
      com.example.combined.grpc.EmployeesResponse> getGetEmployeesByDistrictOrderedBySalaryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetEmployeesByDistrictOrderedBySalary",
      requestType = com.example.combined.grpc.EmployeeByDistrictOrderedRequest.class,
      responseType = com.example.combined.grpc.EmployeesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByDistrictOrderedRequest,
      com.example.combined.grpc.EmployeesResponse> getGetEmployeesByDistrictOrderedBySalaryMethod() {
    io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeByDistrictOrderedRequest, com.example.combined.grpc.EmployeesResponse> getGetEmployeesByDistrictOrderedBySalaryMethod;
    if ((getGetEmployeesByDistrictOrderedBySalaryMethod = EmployeeServiceGrpc.getGetEmployeesByDistrictOrderedBySalaryMethod) == null) {
      synchronized (EmployeeServiceGrpc.class) {
        if ((getGetEmployeesByDistrictOrderedBySalaryMethod = EmployeeServiceGrpc.getGetEmployeesByDistrictOrderedBySalaryMethod) == null) {
          EmployeeServiceGrpc.getGetEmployeesByDistrictOrderedBySalaryMethod = getGetEmployeesByDistrictOrderedBySalaryMethod =
              io.grpc.MethodDescriptor.<com.example.combined.grpc.EmployeeByDistrictOrderedRequest, com.example.combined.grpc.EmployeesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetEmployeesByDistrictOrderedBySalary"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeeByDistrictOrderedRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EmployeeServiceMethodDescriptorSupplier("GetEmployeesByDistrictOrderedBySalary"))
              .build();
        }
      }
    }
    return getGetEmployeesByDistrictOrderedBySalaryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeNamesByBranchRequest,
      com.example.combined.grpc.EmployeeNamesResponse> getGetEmployeeNamesAndRolesByBranchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetEmployeeNamesAndRolesByBranch",
      requestType = com.example.combined.grpc.EmployeeNamesByBranchRequest.class,
      responseType = com.example.combined.grpc.EmployeeNamesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeNamesByBranchRequest,
      com.example.combined.grpc.EmployeeNamesResponse> getGetEmployeeNamesAndRolesByBranchMethod() {
    io.grpc.MethodDescriptor<com.example.combined.grpc.EmployeeNamesByBranchRequest, com.example.combined.grpc.EmployeeNamesResponse> getGetEmployeeNamesAndRolesByBranchMethod;
    if ((getGetEmployeeNamesAndRolesByBranchMethod = EmployeeServiceGrpc.getGetEmployeeNamesAndRolesByBranchMethod) == null) {
      synchronized (EmployeeServiceGrpc.class) {
        if ((getGetEmployeeNamesAndRolesByBranchMethod = EmployeeServiceGrpc.getGetEmployeeNamesAndRolesByBranchMethod) == null) {
          EmployeeServiceGrpc.getGetEmployeeNamesAndRolesByBranchMethod = getGetEmployeeNamesAndRolesByBranchMethod =
              io.grpc.MethodDescriptor.<com.example.combined.grpc.EmployeeNamesByBranchRequest, com.example.combined.grpc.EmployeeNamesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetEmployeeNamesAndRolesByBranch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeeNamesByBranchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.EmployeeNamesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EmployeeServiceMethodDescriptorSupplier("GetEmployeeNamesAndRolesByBranch"))
              .build();
        }
      }
    }
    return getGetEmployeeNamesAndRolesByBranchMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EmployeeServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EmployeeServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EmployeeServiceStub>() {
        @java.lang.Override
        public EmployeeServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EmployeeServiceStub(channel, callOptions);
        }
      };
    return EmployeeServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static EmployeeServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EmployeeServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EmployeeServiceBlockingV2Stub>() {
        @java.lang.Override
        public EmployeeServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EmployeeServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return EmployeeServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EmployeeServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EmployeeServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EmployeeServiceBlockingStub>() {
        @java.lang.Override
        public EmployeeServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EmployeeServiceBlockingStub(channel, callOptions);
        }
      };
    return EmployeeServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EmployeeServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EmployeeServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EmployeeServiceFutureStub>() {
        @java.lang.Override
        public EmployeeServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EmployeeServiceFutureStub(channel, callOptions);
        }
      };
    return EmployeeServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * EmployeeService
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Basic CRUD operations
     * </pre>
     */
    default void createEmployee(com.example.combined.grpc.CreateEmployeeRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateEmployeeMethod(), responseObserver);
    }

    /**
     */
    default void getEmployeeById(com.example.combined.grpc.EmployeeByIdRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetEmployeeByIdMethod(), responseObserver);
    }

    /**
     * <pre>
     * Basic query operations
     * </pre>
     */
    default void getEmployeesByRole(com.example.combined.grpc.EmployeeByRoleRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetEmployeesByRoleMethod(), responseObserver);
    }

    /**
     */
    default void getEmployeesBySalary(com.example.combined.grpc.EmployeeBySalaryRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetEmployeesBySalaryMethod(), responseObserver);
    }

    /**
     */
    default void getEmployeesByDistrict(com.example.combined.grpc.EmployeeByDistrictRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetEmployeesByDistrictMethod(), responseObserver);
    }

    /**
     */
    default void getEmployeesByBranch(com.example.combined.grpc.EmployeeByBranchRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetEmployeesByBranchMethod(), responseObserver);
    }

    /**
     */
    default void getAllEmployees(com.example.combined.grpc.Empty request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAllEmployeesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Enhanced query operations
     * </pre>
     */
    default void getEmployeesByRoleAndBranch(com.example.combined.grpc.EmployeeByRoleAndBranchRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetEmployeesByRoleAndBranchMethod(), responseObserver);
    }

    /**
     */
    default void getEmployeesByDistrictAndBranch(com.example.combined.grpc.EmployeeByDistrictAndBranchRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetEmployeesByDistrictAndBranchMethod(), responseObserver);
    }

    /**
     */
    default void getEmployeesWithSalaryGreaterThan(com.example.combined.grpc.EmployeeByMinSalaryRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetEmployeesWithSalaryGreaterThanMethod(), responseObserver);
    }

    /**
     */
    default void getEmployeesWithSalaryBetween(com.example.combined.grpc.EmployeeBySalaryRangeRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetEmployeesWithSalaryBetweenMethod(), responseObserver);
    }

    /**
     */
    default void getEmployeesByRoleAndMinSalary(com.example.combined.grpc.EmployeeByRoleAndMinSalaryRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetEmployeesByRoleAndMinSalaryMethod(), responseObserver);
    }

    /**
     */
    default void getEmployeesByDistrictOrderedBySalary(com.example.combined.grpc.EmployeeByDistrictOrderedRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetEmployeesByDistrictOrderedBySalaryMethod(), responseObserver);
    }

    /**
     * <pre>
     * Projection operation
     * </pre>
     */
    default void getEmployeeNamesAndRolesByBranch(com.example.combined.grpc.EmployeeNamesByBranchRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeeNamesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetEmployeeNamesAndRolesByBranchMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service EmployeeService.
   * <pre>
   * EmployeeService
   * </pre>
   */
  public static abstract class EmployeeServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return EmployeeServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service EmployeeService.
   * <pre>
   * EmployeeService
   * </pre>
   */
  public static final class EmployeeServiceStub
      extends io.grpc.stub.AbstractAsyncStub<EmployeeServiceStub> {
    private EmployeeServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EmployeeServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EmployeeServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Basic CRUD operations
     * </pre>
     */
    public void createEmployee(com.example.combined.grpc.CreateEmployeeRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateEmployeeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEmployeeById(com.example.combined.grpc.EmployeeByIdRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetEmployeeByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Basic query operations
     * </pre>
     */
    public void getEmployeesByRole(com.example.combined.grpc.EmployeeByRoleRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetEmployeesByRoleMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEmployeesBySalary(com.example.combined.grpc.EmployeeBySalaryRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetEmployeesBySalaryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEmployeesByDistrict(com.example.combined.grpc.EmployeeByDistrictRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetEmployeesByDistrictMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEmployeesByBranch(com.example.combined.grpc.EmployeeByBranchRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetEmployeesByBranchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAllEmployees(com.example.combined.grpc.Empty request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAllEmployeesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Enhanced query operations
     * </pre>
     */
    public void getEmployeesByRoleAndBranch(com.example.combined.grpc.EmployeeByRoleAndBranchRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetEmployeesByRoleAndBranchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEmployeesByDistrictAndBranch(com.example.combined.grpc.EmployeeByDistrictAndBranchRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetEmployeesByDistrictAndBranchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEmployeesWithSalaryGreaterThan(com.example.combined.grpc.EmployeeByMinSalaryRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetEmployeesWithSalaryGreaterThanMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEmployeesWithSalaryBetween(com.example.combined.grpc.EmployeeBySalaryRangeRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetEmployeesWithSalaryBetweenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEmployeesByRoleAndMinSalary(com.example.combined.grpc.EmployeeByRoleAndMinSalaryRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetEmployeesByRoleAndMinSalaryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEmployeesByDistrictOrderedBySalary(com.example.combined.grpc.EmployeeByDistrictOrderedRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetEmployeesByDistrictOrderedBySalaryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Projection operation
     * </pre>
     */
    public void getEmployeeNamesAndRolesByBranch(com.example.combined.grpc.EmployeeNamesByBranchRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeeNamesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetEmployeeNamesAndRolesByBranchMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service EmployeeService.
   * <pre>
   * EmployeeService
   * </pre>
   */
  public static final class EmployeeServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<EmployeeServiceBlockingV2Stub> {
    private EmployeeServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EmployeeServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EmployeeServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     * <pre>
     * Basic CRUD operations
     * </pre>
     */
    public com.example.combined.grpc.EmployeeResponse createEmployee(com.example.combined.grpc.CreateEmployeeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateEmployeeMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.combined.grpc.EmployeeResponse getEmployeeById(com.example.combined.grpc.EmployeeByIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEmployeeByIdMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Basic query operations
     * </pre>
     */
    public com.example.combined.grpc.EmployeesResponse getEmployeesByRole(com.example.combined.grpc.EmployeeByRoleRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEmployeesByRoleMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.combined.grpc.EmployeesResponse getEmployeesBySalary(com.example.combined.grpc.EmployeeBySalaryRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEmployeesBySalaryMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.combined.grpc.EmployeesResponse getEmployeesByDistrict(com.example.combined.grpc.EmployeeByDistrictRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEmployeesByDistrictMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.combined.grpc.EmployeesResponse getEmployeesByBranch(com.example.combined.grpc.EmployeeByBranchRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEmployeesByBranchMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.combined.grpc.EmployeesResponse getAllEmployees(com.example.combined.grpc.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAllEmployeesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Enhanced query operations
     * </pre>
     */
    public com.example.combined.grpc.EmployeesResponse getEmployeesByRoleAndBranch(com.example.combined.grpc.EmployeeByRoleAndBranchRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEmployeesByRoleAndBranchMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.combined.grpc.EmployeesResponse getEmployeesByDistrictAndBranch(com.example.combined.grpc.EmployeeByDistrictAndBranchRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEmployeesByDistrictAndBranchMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.combined.grpc.EmployeesResponse getEmployeesWithSalaryGreaterThan(com.example.combined.grpc.EmployeeByMinSalaryRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEmployeesWithSalaryGreaterThanMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.combined.grpc.EmployeesResponse getEmployeesWithSalaryBetween(com.example.combined.grpc.EmployeeBySalaryRangeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEmployeesWithSalaryBetweenMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.combined.grpc.EmployeesResponse getEmployeesByRoleAndMinSalary(com.example.combined.grpc.EmployeeByRoleAndMinSalaryRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEmployeesByRoleAndMinSalaryMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.combined.grpc.EmployeesResponse getEmployeesByDistrictOrderedBySalary(com.example.combined.grpc.EmployeeByDistrictOrderedRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEmployeesByDistrictOrderedBySalaryMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Projection operation
     * </pre>
     */
    public com.example.combined.grpc.EmployeeNamesResponse getEmployeeNamesAndRolesByBranch(com.example.combined.grpc.EmployeeNamesByBranchRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEmployeeNamesAndRolesByBranchMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service EmployeeService.
   * <pre>
   * EmployeeService
   * </pre>
   */
  public static final class EmployeeServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<EmployeeServiceBlockingStub> {
    private EmployeeServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EmployeeServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EmployeeServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Basic CRUD operations
     * </pre>
     */
    public com.example.combined.grpc.EmployeeResponse createEmployee(com.example.combined.grpc.CreateEmployeeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateEmployeeMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.combined.grpc.EmployeeResponse getEmployeeById(com.example.combined.grpc.EmployeeByIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEmployeeByIdMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Basic query operations
     * </pre>
     */
    public com.example.combined.grpc.EmployeesResponse getEmployeesByRole(com.example.combined.grpc.EmployeeByRoleRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEmployeesByRoleMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.combined.grpc.EmployeesResponse getEmployeesBySalary(com.example.combined.grpc.EmployeeBySalaryRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEmployeesBySalaryMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.combined.grpc.EmployeesResponse getEmployeesByDistrict(com.example.combined.grpc.EmployeeByDistrictRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEmployeesByDistrictMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.combined.grpc.EmployeesResponse getEmployeesByBranch(com.example.combined.grpc.EmployeeByBranchRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEmployeesByBranchMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.combined.grpc.EmployeesResponse getAllEmployees(com.example.combined.grpc.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAllEmployeesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Enhanced query operations
     * </pre>
     */
    public com.example.combined.grpc.EmployeesResponse getEmployeesByRoleAndBranch(com.example.combined.grpc.EmployeeByRoleAndBranchRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEmployeesByRoleAndBranchMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.combined.grpc.EmployeesResponse getEmployeesByDistrictAndBranch(com.example.combined.grpc.EmployeeByDistrictAndBranchRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEmployeesByDistrictAndBranchMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.combined.grpc.EmployeesResponse getEmployeesWithSalaryGreaterThan(com.example.combined.grpc.EmployeeByMinSalaryRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEmployeesWithSalaryGreaterThanMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.combined.grpc.EmployeesResponse getEmployeesWithSalaryBetween(com.example.combined.grpc.EmployeeBySalaryRangeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEmployeesWithSalaryBetweenMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.combined.grpc.EmployeesResponse getEmployeesByRoleAndMinSalary(com.example.combined.grpc.EmployeeByRoleAndMinSalaryRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEmployeesByRoleAndMinSalaryMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.combined.grpc.EmployeesResponse getEmployeesByDistrictOrderedBySalary(com.example.combined.grpc.EmployeeByDistrictOrderedRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEmployeesByDistrictOrderedBySalaryMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Projection operation
     * </pre>
     */
    public com.example.combined.grpc.EmployeeNamesResponse getEmployeeNamesAndRolesByBranch(com.example.combined.grpc.EmployeeNamesByBranchRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEmployeeNamesAndRolesByBranchMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service EmployeeService.
   * <pre>
   * EmployeeService
   * </pre>
   */
  public static final class EmployeeServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<EmployeeServiceFutureStub> {
    private EmployeeServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EmployeeServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EmployeeServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Basic CRUD operations
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.combined.grpc.EmployeeResponse> createEmployee(
        com.example.combined.grpc.CreateEmployeeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateEmployeeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.combined.grpc.EmployeeResponse> getEmployeeById(
        com.example.combined.grpc.EmployeeByIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetEmployeeByIdMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Basic query operations
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.combined.grpc.EmployeesResponse> getEmployeesByRole(
        com.example.combined.grpc.EmployeeByRoleRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetEmployeesByRoleMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.combined.grpc.EmployeesResponse> getEmployeesBySalary(
        com.example.combined.grpc.EmployeeBySalaryRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetEmployeesBySalaryMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.combined.grpc.EmployeesResponse> getEmployeesByDistrict(
        com.example.combined.grpc.EmployeeByDistrictRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetEmployeesByDistrictMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.combined.grpc.EmployeesResponse> getEmployeesByBranch(
        com.example.combined.grpc.EmployeeByBranchRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetEmployeesByBranchMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.combined.grpc.EmployeesResponse> getAllEmployees(
        com.example.combined.grpc.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAllEmployeesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Enhanced query operations
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.combined.grpc.EmployeesResponse> getEmployeesByRoleAndBranch(
        com.example.combined.grpc.EmployeeByRoleAndBranchRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetEmployeesByRoleAndBranchMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.combined.grpc.EmployeesResponse> getEmployeesByDistrictAndBranch(
        com.example.combined.grpc.EmployeeByDistrictAndBranchRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetEmployeesByDistrictAndBranchMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.combined.grpc.EmployeesResponse> getEmployeesWithSalaryGreaterThan(
        com.example.combined.grpc.EmployeeByMinSalaryRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetEmployeesWithSalaryGreaterThanMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.combined.grpc.EmployeesResponse> getEmployeesWithSalaryBetween(
        com.example.combined.grpc.EmployeeBySalaryRangeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetEmployeesWithSalaryBetweenMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.combined.grpc.EmployeesResponse> getEmployeesByRoleAndMinSalary(
        com.example.combined.grpc.EmployeeByRoleAndMinSalaryRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetEmployeesByRoleAndMinSalaryMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.combined.grpc.EmployeesResponse> getEmployeesByDistrictOrderedBySalary(
        com.example.combined.grpc.EmployeeByDistrictOrderedRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetEmployeesByDistrictOrderedBySalaryMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Projection operation
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.combined.grpc.EmployeeNamesResponse> getEmployeeNamesAndRolesByBranch(
        com.example.combined.grpc.EmployeeNamesByBranchRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetEmployeeNamesAndRolesByBranchMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_EMPLOYEE = 0;
  private static final int METHODID_GET_EMPLOYEE_BY_ID = 1;
  private static final int METHODID_GET_EMPLOYEES_BY_ROLE = 2;
  private static final int METHODID_GET_EMPLOYEES_BY_SALARY = 3;
  private static final int METHODID_GET_EMPLOYEES_BY_DISTRICT = 4;
  private static final int METHODID_GET_EMPLOYEES_BY_BRANCH = 5;
  private static final int METHODID_GET_ALL_EMPLOYEES = 6;
  private static final int METHODID_GET_EMPLOYEES_BY_ROLE_AND_BRANCH = 7;
  private static final int METHODID_GET_EMPLOYEES_BY_DISTRICT_AND_BRANCH = 8;
  private static final int METHODID_GET_EMPLOYEES_WITH_SALARY_GREATER_THAN = 9;
  private static final int METHODID_GET_EMPLOYEES_WITH_SALARY_BETWEEN = 10;
  private static final int METHODID_GET_EMPLOYEES_BY_ROLE_AND_MIN_SALARY = 11;
  private static final int METHODID_GET_EMPLOYEES_BY_DISTRICT_ORDERED_BY_SALARY = 12;
  private static final int METHODID_GET_EMPLOYEE_NAMES_AND_ROLES_BY_BRANCH = 13;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_EMPLOYEE:
          serviceImpl.createEmployee((com.example.combined.grpc.CreateEmployeeRequest) request,
              (io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeeResponse>) responseObserver);
          break;
        case METHODID_GET_EMPLOYEE_BY_ID:
          serviceImpl.getEmployeeById((com.example.combined.grpc.EmployeeByIdRequest) request,
              (io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeeResponse>) responseObserver);
          break;
        case METHODID_GET_EMPLOYEES_BY_ROLE:
          serviceImpl.getEmployeesByRole((com.example.combined.grpc.EmployeeByRoleRequest) request,
              (io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse>) responseObserver);
          break;
        case METHODID_GET_EMPLOYEES_BY_SALARY:
          serviceImpl.getEmployeesBySalary((com.example.combined.grpc.EmployeeBySalaryRequest) request,
              (io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse>) responseObserver);
          break;
        case METHODID_GET_EMPLOYEES_BY_DISTRICT:
          serviceImpl.getEmployeesByDistrict((com.example.combined.grpc.EmployeeByDistrictRequest) request,
              (io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse>) responseObserver);
          break;
        case METHODID_GET_EMPLOYEES_BY_BRANCH:
          serviceImpl.getEmployeesByBranch((com.example.combined.grpc.EmployeeByBranchRequest) request,
              (io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse>) responseObserver);
          break;
        case METHODID_GET_ALL_EMPLOYEES:
          serviceImpl.getAllEmployees((com.example.combined.grpc.Empty) request,
              (io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse>) responseObserver);
          break;
        case METHODID_GET_EMPLOYEES_BY_ROLE_AND_BRANCH:
          serviceImpl.getEmployeesByRoleAndBranch((com.example.combined.grpc.EmployeeByRoleAndBranchRequest) request,
              (io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse>) responseObserver);
          break;
        case METHODID_GET_EMPLOYEES_BY_DISTRICT_AND_BRANCH:
          serviceImpl.getEmployeesByDistrictAndBranch((com.example.combined.grpc.EmployeeByDistrictAndBranchRequest) request,
              (io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse>) responseObserver);
          break;
        case METHODID_GET_EMPLOYEES_WITH_SALARY_GREATER_THAN:
          serviceImpl.getEmployeesWithSalaryGreaterThan((com.example.combined.grpc.EmployeeByMinSalaryRequest) request,
              (io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse>) responseObserver);
          break;
        case METHODID_GET_EMPLOYEES_WITH_SALARY_BETWEEN:
          serviceImpl.getEmployeesWithSalaryBetween((com.example.combined.grpc.EmployeeBySalaryRangeRequest) request,
              (io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse>) responseObserver);
          break;
        case METHODID_GET_EMPLOYEES_BY_ROLE_AND_MIN_SALARY:
          serviceImpl.getEmployeesByRoleAndMinSalary((com.example.combined.grpc.EmployeeByRoleAndMinSalaryRequest) request,
              (io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse>) responseObserver);
          break;
        case METHODID_GET_EMPLOYEES_BY_DISTRICT_ORDERED_BY_SALARY:
          serviceImpl.getEmployeesByDistrictOrderedBySalary((com.example.combined.grpc.EmployeeByDistrictOrderedRequest) request,
              (io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeesResponse>) responseObserver);
          break;
        case METHODID_GET_EMPLOYEE_NAMES_AND_ROLES_BY_BRANCH:
          serviceImpl.getEmployeeNamesAndRolesByBranch((com.example.combined.grpc.EmployeeNamesByBranchRequest) request,
              (io.grpc.stub.StreamObserver<com.example.combined.grpc.EmployeeNamesResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCreateEmployeeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.combined.grpc.CreateEmployeeRequest,
              com.example.combined.grpc.EmployeeResponse>(
                service, METHODID_CREATE_EMPLOYEE)))
        .addMethod(
          getGetEmployeeByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.combined.grpc.EmployeeByIdRequest,
              com.example.combined.grpc.EmployeeResponse>(
                service, METHODID_GET_EMPLOYEE_BY_ID)))
        .addMethod(
          getGetEmployeesByRoleMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.combined.grpc.EmployeeByRoleRequest,
              com.example.combined.grpc.EmployeesResponse>(
                service, METHODID_GET_EMPLOYEES_BY_ROLE)))
        .addMethod(
          getGetEmployeesBySalaryMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.combined.grpc.EmployeeBySalaryRequest,
              com.example.combined.grpc.EmployeesResponse>(
                service, METHODID_GET_EMPLOYEES_BY_SALARY)))
        .addMethod(
          getGetEmployeesByDistrictMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.combined.grpc.EmployeeByDistrictRequest,
              com.example.combined.grpc.EmployeesResponse>(
                service, METHODID_GET_EMPLOYEES_BY_DISTRICT)))
        .addMethod(
          getGetEmployeesByBranchMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.combined.grpc.EmployeeByBranchRequest,
              com.example.combined.grpc.EmployeesResponse>(
                service, METHODID_GET_EMPLOYEES_BY_BRANCH)))
        .addMethod(
          getGetAllEmployeesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.combined.grpc.Empty,
              com.example.combined.grpc.EmployeesResponse>(
                service, METHODID_GET_ALL_EMPLOYEES)))
        .addMethod(
          getGetEmployeesByRoleAndBranchMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.combined.grpc.EmployeeByRoleAndBranchRequest,
              com.example.combined.grpc.EmployeesResponse>(
                service, METHODID_GET_EMPLOYEES_BY_ROLE_AND_BRANCH)))
        .addMethod(
          getGetEmployeesByDistrictAndBranchMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.combined.grpc.EmployeeByDistrictAndBranchRequest,
              com.example.combined.grpc.EmployeesResponse>(
                service, METHODID_GET_EMPLOYEES_BY_DISTRICT_AND_BRANCH)))
        .addMethod(
          getGetEmployeesWithSalaryGreaterThanMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.combined.grpc.EmployeeByMinSalaryRequest,
              com.example.combined.grpc.EmployeesResponse>(
                service, METHODID_GET_EMPLOYEES_WITH_SALARY_GREATER_THAN)))
        .addMethod(
          getGetEmployeesWithSalaryBetweenMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.combined.grpc.EmployeeBySalaryRangeRequest,
              com.example.combined.grpc.EmployeesResponse>(
                service, METHODID_GET_EMPLOYEES_WITH_SALARY_BETWEEN)))
        .addMethod(
          getGetEmployeesByRoleAndMinSalaryMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.combined.grpc.EmployeeByRoleAndMinSalaryRequest,
              com.example.combined.grpc.EmployeesResponse>(
                service, METHODID_GET_EMPLOYEES_BY_ROLE_AND_MIN_SALARY)))
        .addMethod(
          getGetEmployeesByDistrictOrderedBySalaryMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.combined.grpc.EmployeeByDistrictOrderedRequest,
              com.example.combined.grpc.EmployeesResponse>(
                service, METHODID_GET_EMPLOYEES_BY_DISTRICT_ORDERED_BY_SALARY)))
        .addMethod(
          getGetEmployeeNamesAndRolesByBranchMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.combined.grpc.EmployeeNamesByBranchRequest,
              com.example.combined.grpc.EmployeeNamesResponse>(
                service, METHODID_GET_EMPLOYEE_NAMES_AND_ROLES_BY_BRANCH)))
        .build();
  }

  private static abstract class EmployeeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EmployeeServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.combined.grpc.CombinedProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EmployeeService");
    }
  }

  private static final class EmployeeServiceFileDescriptorSupplier
      extends EmployeeServiceBaseDescriptorSupplier {
    EmployeeServiceFileDescriptorSupplier() {}
  }

  private static final class EmployeeServiceMethodDescriptorSupplier
      extends EmployeeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    EmployeeServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (EmployeeServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EmployeeServiceFileDescriptorSupplier())
              .addMethod(getCreateEmployeeMethod())
              .addMethod(getGetEmployeeByIdMethod())
              .addMethod(getGetEmployeesByRoleMethod())
              .addMethod(getGetEmployeesBySalaryMethod())
              .addMethod(getGetEmployeesByDistrictMethod())
              .addMethod(getGetEmployeesByBranchMethod())
              .addMethod(getGetAllEmployeesMethod())
              .addMethod(getGetEmployeesByRoleAndBranchMethod())
              .addMethod(getGetEmployeesByDistrictAndBranchMethod())
              .addMethod(getGetEmployeesWithSalaryGreaterThanMethod())
              .addMethod(getGetEmployeesWithSalaryBetweenMethod())
              .addMethod(getGetEmployeesByRoleAndMinSalaryMethod())
              .addMethod(getGetEmployeesByDistrictOrderedBySalaryMethod())
              .addMethod(getGetEmployeeNamesAndRolesByBranchMethod())
              .build();
        }
      }
    }
    return result;
  }
}
