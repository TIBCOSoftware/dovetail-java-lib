---
title: dovetail-java-lib
weight: 4603
---
# dovetail-java-lib

Project Dovetail™ Java library provides a runtime environment to interpret and execute smart contract flows developed using Project Dovetail™ Studio. It provides hooks to plugin any blockchain or distributed ledger platforms through a set of interfaces that must be implemented.

- **com.tibco.dovetail.runtime.services.IDataService**: provides read/write access to platform's ledgers 
- **com.tibco.dovetail.runtime.services.IEventService**: provides capability for smart contract to pulish events to platform's eventing stream
- **com.tibco.dovetail.runtime.services.ILogService**: provides access to platform's logging mechanism
- **com.tibco.dovetail.runtime.services.IContainerService**: entry point to get get access to platform's data, eventing and logging services
- **com.tibco.dovetail.runtime.transaction.ITransactionService**: resolve transaction input and provides transaction level security service

Project Dovetail™ provides [SmartContract TXN Trigger](https://github.com/TIBCOSoftware/dovetail-contrib/tree/master/SmartContract/trigger/transaction) to dispatch smart contract invocation to the correct handler, if you have needs to create your own trigger, it must implement com.tibco.dovetail.runtime.trigger.ITrigger interface.

Project Dovetail™ Java runtime dynamically loads activities and triggers in the smart contract application json model file, the java package name of these activities and tiggers must follow this pattern [lowercase(category name)].activity.[activity name] and [lowercase(category name)].trigger.[trigger name] respectively.

Project Dovetail™ Java runtime supports JDK1.8+



